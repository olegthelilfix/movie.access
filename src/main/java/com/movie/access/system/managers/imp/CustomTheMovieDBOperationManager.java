package com.movie.access.system.managers.imp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.entitys.FullMovieInfo;
import com.movie.access.system.entitys.MovieList;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import com.movie.access.system.themoviedb.operations.AbstractApiOperation;
import com.movie.access.system.themoviedb.operations.DiscoverMovieOperation;
import com.movie.access.system.themoviedb.operations.GenreVoteAveragesOperation;
import com.movie.access.system.themoviedb.operations.MovieInfoOperation;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Менеджер, управляющий взаимодейтсвием с api themoviedb.com.
 * Поддерживает многопоточное взаимодействие, за счет того, что для выполнения каждой операции
 * создается новый экземпляр объекта отвечающий за взаимодействие с определенным внешним методом,
 * со своим http клиентом.
 * TODO TESTS
 * @author Aleksandrov Oleg
 */
@AllArgsConstructor
public class CustomTheMovieDBOperationManager implements TheMovieDBOperationManager
{
    private String apiKey;

    /**
     * Функция отвечает за получение информации по фильму,
     * и преобразует полученные данные во внтуренний формат сервиса.
     * @param movieId id фильма, инфу по которому нужно получить от themoviedb
     * @return информацию по запрашиваемому фильму.
     */
    @Override
    public FullMovieInfo getMovieInfo(int movieId) throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation movieDBApiOperation = new MovieInfoOperation(apiKey, movieId);

        return getObjectMapperWithOutFailOnUnknownProperties().readValue(movieDBApiOperation.execute(),
                                                                         FullMovieInfo.class);
    }

    /**
     * Функция отвечает за получение списка фильмов удволетворяющих заданым параметрам,
     * и преобразует полученные данные во внтуренний формат сервиса.
     * @param params набор критериев, по которому должен произойти отбор фильмов.
     *              подробнее https://developers.themoviedb.org/3/discover/movie-discover
     * @return Список фильмов удволетворяющих заданым критериям.
     */
    @Override
    public MovieList getMovieList(Map<String, String> params) throws TheMovieDBOperationException,
                                                                     IOException,
                                                                     URISyntaxException
    {
        AbstractApiOperation discoverMovieOperation = new DiscoverMovieOperation(apiKey, params);

        return getObjectMapperWithOutFailOnUnknownProperties().readValue(discoverMovieOperation.execute(),
                                                                         MovieList.class);
    }

    /**
     * Функция отвечает за получения средней оценки каждого из фильмов в определеном жаре по странично.
     * @param page Номер страницы, с которого нужно получить информаци.
     * @param genreId id жанра, оценки по которому необходимо получить.
     * @return Список средних оценок для фильмов в определеном жанре.
     */
    @Override
    public GenreAverageList getGenreAverageList(int page, int genreId) throws TheMovieDBOperationException,
                                                                              IOException,
                                                                              URISyntaxException
    {
        AbstractApiOperation genreVoteAveragesOperation = new GenreVoteAveragesOperation(apiKey, page, genreId);

        return getObjectMapperWithOutFailOnUnknownProperties().readValue(genreVoteAveragesOperation.execute(),
                                                                         GenreAverageList.class);
    }

    /**
     * Функция создает {@link ObjectMapper} с опцией игнорировать неизвестные опции.
     */
    private ObjectMapper getObjectMapperWithOutFailOnUnknownProperties()
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }
}
