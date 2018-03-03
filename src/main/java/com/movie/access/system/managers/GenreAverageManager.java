package com.movie.access.system.managers;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.entitys.GenreAverage;
import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.themoviedb.operations.TheMovieDBOperationExecutor;
import lombok.Synchronized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GenreAverageManager
{
    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    private Map<Integer, AverageInfo> genreAverageMap = new ConcurrentHashMap<>();

    @Synchronized
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
                newAverageInfo.setCompletionPercent(-1.0);
                newAverageInfo.setAverage(-1.0);

                System.out.println(e);
            }
        });

        return newAverageInfo;
    }

    private void calculateGenreAverage(Integer genreId) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        GenreAverageList genreAveragesList = TheMovieDBOperationExecutor.getGenreAverageList(1, genreId);

        double sum = getGenreAveragesSum(genreAveragesList);
        int movieCount = genreAveragesList.getResults().size();

        AverageInfo averageInfo = genreAverageMap.get(genreId);

//        int pageCount = genreAveragesList.getTotal_pages();
        int pageCount = 10;

        for(int i = 2; i <= pageCount; ++i)
        {
            GenreAverageList newGenreAverageList = TheMovieDBOperationExecutor.getGenreAverageList(i, genreId);
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
