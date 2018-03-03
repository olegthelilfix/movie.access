package com.movie.access.system.themoviedb.operations;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

/**
 * TODO TEXT and TEST
 * @author Aleksandrov Oleg
 */
public class GenreVoteAveragesOperation extends AbstractApiOperation
{
    private static final String uri = "https://api.themoviedb.org/3/genre/";

//        @Value("${theMovieDB.apiKey}")
    private String apiKey = "72b56103e43843412a992a8d64bf96e9";

    private int page;
    private int genreId;

    public GenreVoteAveragesOperation(int page, int genreId)
    {
        this.page = page;
        this.genreId = genreId;
    }

    @Override
    protected HttpUriRequest formRequest() throws URISyntaxException
    {
        URIBuilder uriBuilder = new URIBuilder(uri + genreId + "/movies");

        uriBuilder.setParameter("api_key", apiKey);
        uriBuilder.setParameter("page", String.valueOf(page));
        uriBuilder.setParameter("genre_id", String.valueOf(genreId));

        return new HttpGet(uriBuilder.build());
    }
}
