package com.movie.access.system.themoviedb.operations;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class AbstractApiOperation
{
    protected abstract HttpUriRequest formRequest() throws URISyntaxException;

    public String execute() throws IOException, URISyntaxException
    {
        HttpUriRequest uriRequest = formRequest();

        HttpResponse response = executeRequest(uriRequest);

        return EntityUtils.toString(response.getEntity());
    }

    protected HttpResponse executeRequest(HttpUriRequest request) throws IOException
    {
        HttpClient client = HttpClients.createDefault();

        return client.execute(request);
    }
}
