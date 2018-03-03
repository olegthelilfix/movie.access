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


/**
 * Базовый класс при реализации запросов к API themoviedb.com.
 * Регламинтирует основные операции работы с веб-клиентом, для выполнения запроса,
 * и получения полезной информации из тела запроса.
 *
 * Для создания, новой операции связанной с доступок к API themoviedb.com,
 * неоходимо создать класс наследующий данный, который реализает логику формирования запроса.
 */
public abstract class AbstractApiOperation
{
    /**
     * Функция должна реализовывать формирование запроса к API.
     */
    protected abstract HttpUriRequest formRequest() throws URISyntaxException;

    /**
     * Функция выполнает запрос через {@link HttpClient} и обрабатывает результат запроса.
     *
     * @return результат выполнения запроса.
     * @throws TheMovieDBOperationException если запрос вернул не 200 код ответа.
     */
    public String execute() throws IOException, URISyntaxException, TheMovieDBOperationException
    {
        HttpUriRequest uriRequest = formRequest();
        HttpResponse response = executeRequest(uriRequest);

        String responseEntityAsString = EntityUtils.toString(response.getEntity());

        // проверяем не получили ли мы не 200 код в ответе.
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
        {
            JSONObject errorJson = new JSONObject(responseEntityAsString);
            String errorMessage = errorJson.optString("status_message", "something went wrong");

            throw new TheMovieDBOperationException(errorMessage);
        }

        return responseEntityAsString;
    }

    private HttpResponse executeRequest(HttpUriRequest request) throws IOException
    {
        HttpClient client = HttpClients.createDefault();

        return client.execute(request);
    }
}
