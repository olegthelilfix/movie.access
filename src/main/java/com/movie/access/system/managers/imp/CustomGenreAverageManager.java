package com.movie.access.system.managers.imp;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.entitys.GenreAverage;
import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.entitys.GenreAverageManagerSettings;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Менеджер, отвечает за расчет среднего.
 * Как только к нему поступает запрос он проверяет есть ли уже необходимые данные
 * и если нет, инициирует процесс сборки данных в отдельном потоке,
 * а также информирует, что процесс начался.
 * Если необходимые данные пристутсвует, сервис информирует о прогрессе.
 *
 * @author Aleksandrov Oleg
 */
@Slf4j
public class CustomGenreAverageManager implements GenreAverageManager
{
    private final static int PAGE_LIMIT = 1000;
    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    @NonNull
    private TheMovieDBOperationManager theMovieDBOperationManager;

    private Map<Integer, AverageInfo> genreAverageMap = new ConcurrentHashMap<>();

    public CustomGenreAverageManager(TheMovieDBOperationManager theMovieDBOperationManager,
                                     GenreAverageManagerSettings settings)
    {
        this.theMovieDBOperationManager = theMovieDBOperationManager;

        // По таймеру проверяем не надо ли нам очистить условно ненужные данные.
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(() ->
                        {

                            for (Integer key : genreAverageMap.keySet())
                            {
                                AverageInfo value = genreAverageMap.get(key);

                                Long expTime = System.currentTimeMillis() -
                                               TimeUnit.MICROSECONDS.convert(settings.getExpiredTime(), settings.getExpiredTimeUnit());

                                if (expTime >= value.getCreateDate())
                                {
                                    genreAverageMap.remove(key);
                                }
                            }

                },
                settings.getClearDelay(),
                settings.getClearDelay(),
                settings.getClearTimeUnit());
    }

    /**
     * Функция возвращает информацию о средней оценке по жанру.
     * Если данных нет инициирует начало подсчета.
     */
    @Synchronized
    @Override
    public AverageInfo getAverage(Integer genreId)
    {
        if(genreAverageMap.containsKey(genreId))
        {
            return genreAverageMap.get(genreId);
        }

        AverageInfo newAverageInfo = new AverageInfo();
        genreAverageMap.put(genreId, newAverageInfo);

        executorService.submit(() ->
        {
            try
            {
                calculateGenreAverage(genreId);
            }
            catch (URISyntaxException | IOException | TheMovieDBOperationException e)
            {
                newAverageInfo.setCompletionPercent(Double.NaN);
                newAverageInfo.setAverage(Double.NaN);

                log.warn("Calculate genre average fail. Cuz:", e);
            }
        });

        return newAverageInfo;
    }

    // TODO рефакторинг для метода. мб рекурсия?
    private void calculateGenreAverage(Integer genreId) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        // запрашиваем первую страницу с даннымы, из нее мы узнаем сколько еще страниц осталось.
        GenreAverageList genreAveragesList = theMovieDBOperationManager.getGenreAverageList(1, genreId);

        // для посчета его более чем достаточно, тк api отадает
        // максимум инфу о 20000 фильмах, и каждая оценка <=10.
        double sum = getGenreAveragesSum(genreAveragesList);
        int movieCount = genreAveragesList.getResults().size();

        AverageInfo averageInfo = genreAverageMap.get(genreId);

        int pageCount = genreAveragesList.getTotal_pages();

        //why? See https://www.themoviedb.org/talk/54e9c6109251412eb1003cc0
        if(pageCount > PAGE_LIMIT)
        {
            pageCount = PAGE_LIMIT;
        }

        for(int i = 2; i <= pageCount; ++i)
        {
            GenreAverageList newGenreAverageList = theMovieDBOperationManager.getGenreAverageList(i, genreId);

            sum += getGenreAveragesSum(newGenreAverageList);
            movieCount += newGenreAverageList.getResults().size();

            averageInfo.setCompletionPercent(i * 100.0 / pageCount);

            log.debug("page:{}, sum:{}",i, sum);
        }

        averageInfo.setAverage(sum / movieCount);
    }

    private double getGenreAveragesSum(GenreAverageList genreAveragesList)
    {
        return genreAveragesList.getResults().stream().mapToDouble(GenreAverage::getVote_average).sum();
    }
}
