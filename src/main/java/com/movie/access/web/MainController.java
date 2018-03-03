package com.movie.access.web;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.entitys.GenreAverage;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.entitys.MovieInfo;
import com.movie.access.system.entitys.MovieList;
import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.themoviedb.operations.TheMovieDBOperationExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * TODO TEXT and TEST
 * @author Aleksandrov Oleg
 */
@RestController
@RequestMapping("/movie")
@EnableWebMvc
public class MainController
{
    private GenreAverageManager genreAverageManager = new GenreAverageManager();

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public MovieInfo getMovieInfo(@RequestParam int movieId) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        return TheMovieDBOperationExecutor.getMovieInfo(movieId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public MovieList getMovieList(@RequestParam Map<String, String> params) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        return TheMovieDBOperationExecutor.getMovieList(params);
    }

    @RequestMapping(value = "/average", method = RequestMethod.GET)
    @ResponseBody
    public AverageInfo getAverage(@RequestParam int genreId)
    {
        return genreAverageManager.getAverage(genreId);
    }
}
