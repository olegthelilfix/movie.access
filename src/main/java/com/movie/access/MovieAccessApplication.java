package com.movie.access;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.shared.MovieInfo;
import com.movie.access.system.themoviedb.operations.TheMovieDBOperationExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MovieAccessApplication
{
    @Bean
    public LoadingCache<Integer, MovieInfo> createMovieCache()
    {
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)
                .weakKeys()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, MovieInfo>()
                {
                    public MovieInfo load(Integer movieId) throws URISyntaxException,
                                                              IOException,
                                                              TheMovieDBOperationException
                    {
                        return TheMovieDBOperationExecutor.getMovieInfo(movieId);
                    }
                });
    }

	public static void main(String[] args)
    {
		SpringApplication.run(MovieAccessApplication.class, args);
	}

}
