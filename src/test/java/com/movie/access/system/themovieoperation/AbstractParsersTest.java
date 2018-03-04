package com.movie.access.system.themovieoperation;

import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.themoviedb.operations.AbstractApiOperation;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class AbstractParsersTest
{
    protected abstract AbstractApiOperation createWithCorrectParams();
    protected abstract AbstractApiOperation createWithUnCorrectApiKey();
    protected abstract AbstractApiOperation createWithUnCorrectOtherParams();

    @Test
    public void executeWitCorrectParams() throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation abstractApiOperation = createWithCorrectParams();

        abstractApiOperation.execute();
    }

    @Test(expected = TheMovieDBOperationException.class)
    public void executeWithUnCorrectApiKey() throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation abstractApiOperation = createWithUnCorrectApiKey();

        abstractApiOperation.execute();
    }

    @Test(expected = TheMovieDBOperationException.class)
    public void executeWithUnOtherParams() throws TheMovieDBOperationException, IOException, URISyntaxException
    {
        AbstractApiOperation abstractApiOperation = createWithUnCorrectOtherParams();

        abstractApiOperation.execute();
    }
}
