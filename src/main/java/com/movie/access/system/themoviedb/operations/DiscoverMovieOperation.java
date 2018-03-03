package com.movie.access.system.themoviedb.operations;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * Класс реализует получения списка фильмов удволетворяющих определенным критериям,
 * а также сортирую фильмы по определенным критериям.
 * Подробнее https://developers.themoviedb.org/3/discover/movie-discover
 */
@RequiredArgsConstructor
public class DiscoverMovieOperation extends AbstractApiOperation
{
    private static final String uri = "https://api.themoviedb.org/3/discover/movie";

    //    @Value("${theMovieDB.apiKey}")
    private String apiKey = "72b56103e43843412a992a8d64bf96e9";

    @NonNull
    private Map<String, String> params;

    @Override
    protected HttpUriRequest formRequest() throws URISyntaxException
    {
        URIBuilder uriBuilder = new URIBuilder(uri);

        params.forEach((key, value) ->
        {
            if(key != null && value != null)
            {
                uriBuilder.setParameter(key, value);
            }
        });

        uriBuilder.setParameter("api_key", apiKey);

        return new HttpGet(uriBuilder.build());
    }
}
