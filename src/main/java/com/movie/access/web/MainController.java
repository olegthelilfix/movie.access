package com.movie.access.web;

import com.google.common.cache.LoadingCache;
import com.movie.access.system.shared.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v0.1")
@EnableWebMvc
public class MainController
{
    @Autowired
    LoadingCache<Integer, Movie> movieCache;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Movie getMovieInfo(@RequestParam int movieId) throws ExecutionException
    {
        return movieCache.get(movieId);
    }
}
