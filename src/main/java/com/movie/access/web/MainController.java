package com.movie.access.web;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.entitys.FullMovieInfo;
import com.movie.access.system.entitys.MovieList;
import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Контроллер реализующий все API методы сервиса.
 * Его задача, с минимальным количеством действий перепоручить
 * выполнения запроса одному из компонентов системы, и вернуть получившийся ответ.
 * @author Aleksandrov Oleg
 */
@RestController
@RequestMapping("/movie")
@EnableWebMvc
@Slf4j
public class MainController
{
    @Autowired
    private TheMovieDBOperationManager theMovieDBOperationManager;

    @Autowired
    private GenreAverageManager customGenreAverageManager;

    /**
     * При выполнении этого запроса происходит обращение к в нешнему сервису.
     * В результате чего извлекается информация о фильме
     * (подробнее о том какие данные отдает метод можно прочесть здесь
     * https://developers.themoviedb.org/3/movies/get-movie-details)
     * В результате выполнения запроса извлекается только часть данных(подробнее см класс {@link FullMovieInfo}).
     * @param movieId id фильма по которому необходимо получить инфу.
     * @return информация по фильму.
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FullMovieInfo> getMovieInfo(@RequestParam int movieId) throws URISyntaxException,
                                                                                        IOException,
                                                                                        TheMovieDBOperationException
    {
        log.debug("Get /movie/info with movieId:{}", movieId);

        return ResponseEntity.ok(theMovieDBOperationManager.getMovieInfo(movieId));
    }

    /**
     * При выполнении этого запроса происходит обращения к внешнему сервису,
     * В результате чего мы получаем список фильмов, с краткой информацией о фильме,
     * (подробнее см класс {@link MovieList})удволетворяющих некому критерию.
     * @param params критерии по которым необходимо искать фильмы.
     * @return список фильмов удволетворяющий заданым критериям
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MovieList> getMovieList(@RequestParam Map<String, String> params) throws URISyntaxException,
                                                                                                   IOException,
                                                                                                   TheMovieDBOperationException
    {
        log.debug("Get /movie/list with params:{}", params);

        if(params.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(theMovieDBOperationManager.getMovieList(params));
    }

    /**
     * Предоставляет информацию о средней оценки фильмов по жанру.
     * Если необходимой информации нет, инициирует, процесс выполнения подсчтета.
     * А пользователь получает объект, который содержит два поля:
     * - средняя оценка
     * - процент готовности
     * При первом запросе, оба поля будут содержать нули.
     * При последующих запросах, значение процента готовности будет обновляется,
     * отражая то, сколько процентов данных обработанно.
     * О готовности информации будет свидетельствовать число больше нуля в поле средней оценки и
     * в поле процент готовности значение 100.0
     * В случае возникновения ошибки при процессе подсчета(например указан неверный id жанра) оба поля примут значение NaN.
     * @param genreId id жанра
     */
    @RequestMapping(value = "/average", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AverageInfo> getAverageInfo(@RequestParam int genreId)
    {
        log.debug("Get /movie/average with genreId:{}", genreId);

        return ResponseEntity.ok(customGenreAverageManager.getAverage(genreId));
    }
}
