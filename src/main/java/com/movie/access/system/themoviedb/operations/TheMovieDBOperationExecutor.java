package com.movie.access.system.themoviedb.operations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.shared.Movie;

import java.io.IOException;
import java.net.URISyntaxException;

public class TheMovieDBOperationExecutor
{
    public static Movie getMovieInfo(int movieId) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation movieDBApiOperation = new MovieInfoOperation(movieId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper.readValue(movieDBApiOperation.execute(), Movie.class);
    }
}
