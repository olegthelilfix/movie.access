package com.movie.access.configurations;

import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.managers.imp.CustomGenreAverageManager;
import com.movie.access.system.managers.imp.CustomTheMovieDBOperationManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:settings.properties")
public class AppConfiguration
{
    @Value("${themovidb.api.key}")
    private String apiKey;

    @Bean(name = "theMovieDBOperationManager")
    public TheMovieDBOperationManager createTheMovieDBOperationManager()
    {
        return new CustomTheMovieDBOperationManager(apiKey);
    }

    @Bean
    public GenreAverageManager createGenreAverageManager()
    {
        return new CustomGenreAverageManager(createTheMovieDBOperationManager());
    }
}
