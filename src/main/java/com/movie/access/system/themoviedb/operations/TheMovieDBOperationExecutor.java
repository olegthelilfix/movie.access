package com.movie.access.system.themoviedb.operations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.shared.MovieInfo;
import com.movie.access.system.shared.MovieList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class TheMovieDBOperationExecutor
{
    public static MovieInfo getMovieInfo(int movieId) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation movieDBApiOperation = new MovieInfoOperation(movieId);

        return getCustomObjectMapper().readValue(movieDBApiOperation.execute(), MovieInfo.class);
    }

    public static MovieList getMovieList(Map<String, String> params) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation discoverMovieOperation = new DiscoverMovieOperation(params);

        return getCustomObjectMapper().readValue(discoverMovieOperation.execute(), MovieList.class);
    }

    private static ObjectMapper getCustomObjectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }
}
