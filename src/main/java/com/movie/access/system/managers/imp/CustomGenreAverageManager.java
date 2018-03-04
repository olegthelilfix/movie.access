package com.movie.access.system.managers.imp;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.entitys.GenreAverage;
import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO TEXT and TEST
 * @author Aleksandrov Oleg
 */
@Slf4j
@RequiredArgsConstructor
public class CustomGenreAverageManager implements GenreAverageManager
{
    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    @NonNull
    private TheMovieDBOperationManager theMovieDBOperationManager;

    // TODO очиста по времени для объекта + создавать через inject
    private Map<Integer, AverageInfo> genreAverageMap = new ConcurrentHashMap<>();

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

    // TODO рефакторинг для метода
    private void calculateGenreAverage(Integer genreId) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        GenreAverageList genreAveragesList = theMovieDBOperationManager.getGenreAverageList(1, genreId);

        double sum = getGenreAveragesSum(genreAveragesList);
        int movieCount = genreAveragesList.getResults().size();

        AverageInfo averageInfo = genreAverageMap.get(genreId);

        int pageCount = genreAveragesList.getTotal_pages();

        for(int i = 2; i <= pageCount; ++i)
        {
            GenreAverageList newGenreAverageList = theMovieDBOperationManager.getGenreAverageList(i, genreId);
            sum = getGenreAveragesSum(newGenreAverageList);
            movieCount += newGenreAverageList.getResults().size();

            averageInfo.setCompletionPercent(i * 100.0 / pageCount);
        }

        averageInfo.setAverage(sum / movieCount);

    }

    private double getGenreAveragesSum(GenreAverageList genreAveragesList)
    {
        return genreAveragesList.getResults().stream().mapToDouble(GenreAverage::getVote_average).sum();
    }
}
