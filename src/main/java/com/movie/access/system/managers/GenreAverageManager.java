package com.movie.access.system.managers;

import com.movie.access.system.entitys.AverageInfo;

public interface GenreAverageManager
{
    AverageInfo getAverage(Integer genreId);
}
