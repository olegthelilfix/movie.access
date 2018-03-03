package com.movie.access.system.themoviedb.operations;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

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

        uriBuilder.setParameter("api_key", apiKey);

        params.forEach((key, value) ->
        {
            if(key != null && value != null)
            {
                uriBuilder.setParameter(key, value);
            }
        });

        return new HttpGet(uriBuilder.build());
    }
}
