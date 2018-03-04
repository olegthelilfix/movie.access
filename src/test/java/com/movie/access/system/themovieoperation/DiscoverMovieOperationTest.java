package com.movie.access.system.themovieoperation;

import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.themoviedb.operations.AbstractApiOperation;
import com.movie.access.system.themoviedb.operations.DiscoverMovieOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class DiscoverMovieOperationTest extends AbstractParsersTest
{
    @Override
    protected AbstractApiOperation createWithCorrectParams()
    {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        return new DiscoverMovieOperation("72b56103e43843412a992a8d64bf96e9", params);
    }

    @Override
    protected AbstractApiOperation createWithUnCorrectApiKey()
    {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        return new DiscoverMovieOperation("11", params);
    }

    @Override
    protected AbstractApiOperation createWithUnCorrectOtherParams()
    {
        Map<String, String> params = new HashMap<>();
        params.put("page", "-1");
        return new DiscoverMovieOperation("72b56103e43843412a992a8d64bf96e9", params);
    }
}
