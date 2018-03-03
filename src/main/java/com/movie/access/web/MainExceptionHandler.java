package com.movie.access.web;

import com.movie.access.system.errors.TheMovieDBOperationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(TheMovieDBOperationException.class)
    protected ResponseEntity<ServerErrorException> handleAPIFailOperation(TheMovieDBOperationException exception)
    {
        return new ResponseEntity<>(new ServerErrorException(exception.getMessage()),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ServerErrorException> handleApplicationFailOperation()
    {
        return new ResponseEntity<>(new ServerErrorException("Application error."),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    private static class ServerErrorException
    {
        private String message;
    }
}
