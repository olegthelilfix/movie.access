package com.movie.access.web;

import com.movie.access.system.errors.TheMovieDBOperationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * TODO TEXT and TEST
 * @author Aleksandrov Oleg
 */
@ControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(TheMovieDBOperationException.class)
    protected ResponseEntity<ServerErrorDescription> handleAPIFailOperation(TheMovieDBOperationException exception)
    {
        return new ResponseEntity<>(new ServerErrorDescription(exception.getMessage()),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ServerErrorDescription> handleApplicationFailOperation()
    {
        return new ResponseEntity<>(new ServerErrorDescription("Application error."),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    private static class ServerErrorDescription
    {
        private String message;
    }
}
