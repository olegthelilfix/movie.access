package com.movie.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ConcurrentHashMap;


@SpringBootApplication
public class MovieAccessApplication
{
	@Bean
    public ConcurrentHashMap<Integer, Double> getGenreAverageMap()
    {
        return new ConcurrentHashMap<>();
    }

	public static void main(String[] args)
    {
		SpringApplication.run(MovieAccessApplication.class, args);
	}

}
