package com.movie.access.system.managers;

import com.movie.access.system.entitys.GenreAverageList;
import com.movie.access.system.entitys.FullMovieInfo;
import com.movie.access.system.entitys.MovieList;
import com.movie.access.system.errors.TheMovieDBOperationException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Базовый интерфейст, описывающий основные методы взаимодействия сервиса,
 * с внешним сервисом themoviedv.com
 *
 * @author Aleksandrov Oleg
 */
public interface TheMovieDBOperationManager
{
    /**
     * Метод, должен реализовывать получение информации о фильме по его id в themoviedb.com.
     * @param movieId id фильма по которому небходимо получить информацию
     */
    FullMovieInfo getMovieInfo(int movieId) throws TheMovieDBOperationException, IOException, URISyntaxException;

    /**
     * Метод, должен реализовывать получения списка фильмов, удволятверящим определеным критериям.
     * @param params набор критериев, по которому должен произойти отбор фильмов.
     */
    MovieList getMovieList(Map<String, String> params) throws TheMovieDBOperationException, IOException, URISyntaxException;

    /**
     * Метод, должен предоставлять список средник оценок по каждому из фильмов в определенном жанре.
     * @param page Номер страницы, с которого нужно получить информаци.
     * @param genreId id жанра, оценки по которому необходимо получить.
     */
    GenreAverageList getGenreAverageList(int page, int genreId) throws TheMovieDBOperationException, IOException, URISyntaxException;
}
