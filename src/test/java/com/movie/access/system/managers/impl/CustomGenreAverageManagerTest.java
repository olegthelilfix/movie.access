package com.movie.access.system.managers.impl;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.entitys.GenreAverage;
import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.entitys.GenreAverageManagerSettings;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.managers.imp.CustomGenreAverageManager;
import com.movie.access.system.managers.imp.CustomTheMovieDBOperationManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

public class CustomGenreAverageManagerTest
{
    private static final int correctGenreId = 10;
    private CustomGenreAverageManager customGenreAverageManager;

    private CustomTheMovieDBOperationManager theMovieDBOperationManager = Mockito.mock(CustomTheMovieDBOperationManager.class);

    @Before
    public void init() throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        List<GenreAverage> averageList = new ArrayList<>();
        GenreAverage genreAverage = new GenreAverage();
        genreAverage.setVote_average(10);
        averageList.add(genreAverage);

        for(int i=1; i <= 10; ++i)
        {
            GenreAverageList genreAverageList = new GenreAverageList();
            genreAverageList.setPage(i);
            genreAverageList.setTotal_pages(10);
            genreAverageList.setResults(averageList);

            when(theMovieDBOperationManager.getGenreAverageList(i, correctGenreId)).thenReturn(genreAverageList);
            when(theMovieDBOperationManager.getGenreAverageList(i, -1)).thenThrow(new TheMovieDBOperationException());
        }

        GenreAverageManagerSettings settings = new GenreAverageManagerSettings();
        settings.setExpiredTime(2);
        settings.setExpiredTimeUnit(TimeUnit.SECONDS);

        settings.setClearDelay(2L);
        settings.setClearTimeUnit(TimeUnit.SECONDS);

        customGenreAverageManager = new CustomGenreAverageManager(theMovieDBOperationManager, settings);
    }

    @Test
    public void getAverageWithCorrectParam()
    {
        AverageInfo averageInfo = customGenreAverageManager.getAverage(correctGenreId);

        Assert.assertTrue(averageInfo.getAverage() == 0.0);
        Assert.assertTrue(averageInfo.getCompletionPercent() == 0.0);


    }
}
