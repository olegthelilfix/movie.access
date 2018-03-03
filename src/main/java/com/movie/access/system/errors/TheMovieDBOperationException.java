package com.movie.access.system.errors;

import lombok.NoArgsConstructor;

/**
 * TODO TEXT
 * @author Aleksandrov Oleg
 */
@NoArgsConstructor
public class TheMovieDBOperationException extends Exception
{
    public TheMovieDBOperationException(String msg)
    {
        super(msg);
    }
}
