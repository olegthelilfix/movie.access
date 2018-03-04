package com.movie.access.system.themovieoperation;

import com.movie.access.system.themoviedb.operations.AbstractApiOperation;
import com.movie.access.system.themoviedb.operations.GenreVoteAveragesOperation;

public class GenreVoteAveragesOperationTest extends AbstractParsersTest
{
    @Override
    protected AbstractApiOperation createWithCorrectParams()
    {
        return new GenreVoteAveragesOperation("72b56103e43843412a992a8d64bf96e9", 1, 28);
    }

    @Override
    protected AbstractApiOperation createWithUnCorrectApiKey()
    {
        return new GenreVoteAveragesOperation("1", 1, 28);
    }

    @Override
    protected AbstractApiOperation createWithUnCorrectOtherParams()
    {
        return new GenreVoteAveragesOperation("72b56103e43843412a992a8d64bf96e9", -1, 28);
    }
}
