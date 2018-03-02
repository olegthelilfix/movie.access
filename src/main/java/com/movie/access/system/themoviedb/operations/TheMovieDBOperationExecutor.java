package com.movie.access.system.themoviedb.operations;

import com.movie.access.system.errors.TheMovieDBOperationException;

import java.io.IOException;
import java.net.URISyntaxException;

public class TheMovieDBOperationExecutor
{
    public static String getMovieInfo(int movieId) throws TheMovieDBOperationException
    {
        try
        {
            AbstractApiOperation movieDBApiOperation = new MovieInfoOperation(movieId);

            return movieDBApiOperation.execute();
        }
        catch (IOException | URISyntaxException e)
        {
            throw new TheMovieDBOperationException();
        }
    }
}
