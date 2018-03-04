package com.movie.access.web;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.entitys.MovieInfo;
import com.movie.access.system.entitys.MovieList;
import com.movie.access.system.managers.imp.CustomGenreAverageManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import lombok.extern.slf4j.Slf4j;
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

/**
 * TODO TEXT and TEST
 * @author Aleksandrov Oleg
 */
@RestController
@RequestMapping("/movie")
@EnableWebMvc
@Slf4j
public class MainController
{
    @Autowired
    private TheMovieDBOperationManager theMovieDBOperationManager;

    private CustomGenreAverageManager customGenreAverageManager = new CustomGenreAverageManager();

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public MovieInfo getMovieInfo(@RequestParam int movieId) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        log.debug("Get /movie/info with movieId:{}", movieId);

        return theMovieDBOperationManager.getMovieInfo(movieId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public MovieList getMovieList(@RequestParam Map<String, String> params) throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        log.debug("Get /movie/list with params:{}", params);

        return theMovieDBOperationManager.getMovieList(params);
    }

    @RequestMapping(value = "/average", method = RequestMethod.GET)
    @ResponseBody
    public AverageInfo getAverage(@RequestParam int genreId)
    {
        log.debug("Get /movie/average with genreId:{}", genreId);

        return customGenreAverageManager.getAverage(genreId);
    }
}
