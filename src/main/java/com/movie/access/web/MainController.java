package com.movie.access.web;

import com.google.common.cache.LoadingCache;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.shared.MovieInfo;
import com.movie.access.system.shared.MovieList;
import com.movie.access.system.themoviedb.operations.TheMovieDBOperationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/movie")
@EnableWebMvc
public class MainController
{
    @Autowired
    LoadingCache<Integer, MovieInfo> movieCache;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public MovieInfo getMovieInfo(@RequestParam int movieId) throws ExecutionException
    {
        return movieCache.get(movieId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public MovieList getMovieList(@RequestParam Map<String, String> params) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        return TheMovieDBOperationExecutor.getMovieList(params);
    }
}
