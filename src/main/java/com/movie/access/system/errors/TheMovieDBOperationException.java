package com.movie.access.system.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TheMovieDBOperationException extends Exception
{
    public TheMovieDBOperationException(String msg)
    {
        super(msg);
    }
}
