package com.movie.access.system.themoviedb.operations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.access.system.shared.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MovieInfoOperation extends AbstractApiOperation
{
    private static final StringBuilder uri = new StringBuilder("https://api.themoviedb.org/3/movie/");

//    @Value("${theMovieDB.apiKey}")
    private String apiKey = "72b56103e43843412a992a8d64bf96e9";

    private int movieId;

    public MovieInfoOperation(int movieId)
    {
        this.movieId = movieId;
    }

    @Override
    protected HttpUriRequest formRequest() throws URISyntaxException
    {
        URIBuilder uriBuilder = new URIBuilder(uri.append(movieId).toString());

        uriBuilder.setParameter("api_key", apiKey);

        return new HttpGet(uriBuilder.build());
    }
}
