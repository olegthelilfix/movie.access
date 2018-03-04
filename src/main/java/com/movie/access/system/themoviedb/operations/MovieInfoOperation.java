package com.movie.access.system.themoviedb.operations;

import lombok.AllArgsConstructor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

/**
 * Класс реализует получение информации о фильме по его id.
 * подробнее https://developers.themoviedb.org/3/movies/get-movie-details
 * TODO TEXT and TEST
 * @author Aleksandrov Oleg
 */
@AllArgsConstructor
public class MovieInfoOperation extends AbstractApiOperation
{
    private static final String uri = "https://api.themoviedb.org/3/movie/";

    private String apiKey;

    private int movieId;

    @Override
    protected HttpUriRequest formRequest() throws URISyntaxException
    {
        URIBuilder uriBuilder = new URIBuilder(uri + movieId);

        uriBuilder.setParameter("api_key", apiKey);

        return new HttpGet(uriBuilder.build());
    }
}
