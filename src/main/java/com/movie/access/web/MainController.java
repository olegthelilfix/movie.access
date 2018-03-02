package com.movie.access.web;

import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.themoviedb.operations.TheMovieDBOperationExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0.1")
public class MainController
{
    @RequestMapping("/find")
    @ResponseBody
    public ResponseEntity getMovieInfo(@RequestParam int movieId) throws TheMovieDBOperationException
    {
        return ResponseEntity.ok(TheMovieDBOperationExecutor.getMovieInfo(movieId));
    }
}
