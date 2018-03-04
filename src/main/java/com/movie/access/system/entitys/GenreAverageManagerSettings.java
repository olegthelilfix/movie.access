package com.movie.access.system.entitys;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class GenreAverageManagerSettings
{
    private Long clearDelay;
    private TimeUnit clearTimeUnit;

    private int expiredTime;
    private TimeUnit expiredTimeUnit;
}
