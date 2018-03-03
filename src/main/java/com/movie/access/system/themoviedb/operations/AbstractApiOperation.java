package com.movie.access.system.themoviedb.operations;

import com.movie.access.system.errors.TheMovieDBOperationException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class AbstractApiOperation
{
    protected abstract HttpUriRequest formRequest() throws URISyntaxException;

    public String execute() throws IOException, URISyntaxException, TheMovieDBOperationException
    {
        HttpUriRequest uriRequest = formRequest();
        HttpResponse response = executeRequest(uriRequest);

        String responseEntityAsString = EntityUtils.toString(response.getEntity());

        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
        {
            JSONObject errorJson = new JSONObject(responseEntityAsString);
            String errorMessage = errorJson.optString("status_message", "something went wrong");

            throw new TheMovieDBOperationException(errorMessage);
        }

        return responseEntityAsString;
    }

    protected HttpResponse executeRequest(HttpUriRequest request) throws IOException
    {
        HttpClient client = HttpClients.createDefault();

        return client.execute(request);
    }
}
