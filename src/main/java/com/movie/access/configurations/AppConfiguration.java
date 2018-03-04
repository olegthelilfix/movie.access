package com.movie.access.configurations;

import com.movie.access.system.entitys.GenreAverageManagerSettings;
import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.managers.imp.CustomGenreAverageManager;
import com.movie.access.system.managers.imp.CustomTheMovieDBOperationManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:settings.properties")
public class AppConfiguration
{
    @Value("${themovidb.api.key}")
    private String apiKey;

    @Value("${average.manager.clearDelay}")
    private Long clearDelay;

    @Value("${average.manager.clearTimeUnit}")
    private TimeUnit clearTimeUnit;

    @Value("${average.manager.expiredTime}")
    private int expiredTime;

    @Value("${average.manager.expiredTimeUnit}")
    private TimeUnit expiredTimeUnit;


    @Bean
    public TheMovieDBOperationManager createTheMovieDBOperationManager()
    {
        return new CustomTheMovieDBOperationManager(apiKey);
    }

    @Bean
    public GenreAverageManagerSettings createGenreAverageManagerSettings()
    {
        GenreAverageManagerSettings settings = new GenreAverageManagerSettings();

        settings.setClearDelay(clearDelay);
        settings.setClearTimeUnit(clearTimeUnit);

        settings.setExpiredTime(expiredTime);
        settings.setExpiredTimeUnit(expiredTimeUnit);

        return settings;
    }

    @Bean
    public GenreAverageManager createGenreAverageManager()
    {
        return new CustomGenreAverageManager(createTheMovieDBOperationManager(), createGenreAverageManagerSettings());
    }

}
