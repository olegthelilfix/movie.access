package com.movie.access.system.themovieoperation;

import com.movie.access.system.themoviedb.operations.AbstractApiOperation;
import com.movie.access.system.themoviedb.operations.MovieInfoOperation;

public class MovieInfoOperationTest extends AbstractParsersTest
{
    @Override
    protected AbstractApiOperation createWithCorrectParams()
    {
        return new MovieInfoOperation("72b56103e43843412a992a8d64bf96e9", 418457);
    }

    @Override
    protected AbstractApiOperation createWithUnCorrectApiKey()
    {
        return new MovieInfoOperation("72", 418457);
    }

    @Override
    protected AbstractApiOperation createWithUnCorrectOtherParams()
    {
        return new MovieInfoOperation("72b56103e43843412a992a8d64bf96e9", -1);
    }
}
