package com.movie.access.system.themoviedb.operations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.access.system.entitys.GenreAverage;
import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.entitys.MovieInfo;
import com.movie.access.system.entitys.MovieList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Класс прослойка, отвечает за взаимодействие с остальными классами из даного пакета.
 */
public class TheMovieDBOperationExecutor
{
    /**
     * Функция отвечает за получение информации по фильму,
     * и преобразует полученные данные во внтуренний формат сервиса.
     * @param movieId id фильма, инфу по которому нужно получить от themoviedb
     * @return информацию по запрашиваемому фильму.
     */
    public static MovieInfo getMovieInfo(int movieId) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation movieDBApiOperation = new MovieInfoOperation(movieId);

        return getCustomObjectMapper().readValue(movieDBApiOperation.execute(), MovieInfo.class);
    }

    /**
     * Функция отвечает за получение списка фильмов удволетворяющих заданым параметрам,
     * и преобразует полученные данные во внтуренний формат сервиса.
     * @param params параметры для фильтрации подробнее https://developers.themoviedb.org/3/discover/movie-discover
     * @return Список фильмов удволетворяющих заданым критериям.
     */
    public static MovieList getMovieList(Map<String, String> params) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation discoverMovieOperation = new DiscoverMovieOperation(params);

        return getCustomObjectMapper().readValue(discoverMovieOperation.execute(), MovieList.class);
    }

    public static GenreAverageList getGenreAverageList(int page, int genreId) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation genreVoteAvaragesOperation = new GenreVoteAveragesOperation(page, genreId);

        return getCustomObjectMapper().readValue(genreVoteAvaragesOperation.execute(), GenreAverageList.class);
    }

    /**
     * Функция создает {@link ObjectMapper} с опцией игнорировать неизвестные опции.
     */
    private static ObjectMapper getCustomObjectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }
}
