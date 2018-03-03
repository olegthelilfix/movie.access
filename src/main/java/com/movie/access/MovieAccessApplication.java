package com.movie.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Aleksandrov Oleg
 */
@SpringBootApplication
public class MovieAccessApplication
{
	public static void main(String[] args)
    {
		SpringApplication.run(MovieAccessApplication.class, args);
	}

}
