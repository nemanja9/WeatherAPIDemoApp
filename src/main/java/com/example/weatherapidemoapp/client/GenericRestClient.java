package com.example.weatherapidemoapp.client;

import com.example.weatherapidemoapp.exception.ApiException;
import com.example.weatherapidemoapp.exception.ApiExceptionFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GenericRestClient {

    protected RestTemplate restTemplate;

    public GenericRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T get(Class<T> type, String url, Map<String, List<String>> headers, Map<String, String> requestParams) throws ApiException {
        try {
            HttpHeaders httpHeaders = createHeaders(headers);
            StringBuilder sb = new StringBuilder(url);
            if (requestParams != null && !requestParams.isEmpty()) {
                sb.append("?");
                var requestParamNames = new ArrayList<>(requestParams.keySet());
                for (int i = 0; i < requestParamNames.size(); i++) {
                    sb.append(requestParamNames.get(i));
                    sb.append("=");
                    sb.append(requestParams.get(requestParamNames.get(i)));
                    if (i < requestParamNames.size() - 1) {
                        sb.append("&");
                    }
                }
            }
            ResponseEntity<T> responseEntity = restTemplate.exchange(sb.toString(), HttpMethod.GET, new HttpEntity<>(httpHeaders), type);
            return responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            throw ApiExceptionFactory.genericError(e.getStatusCode(), e.getLocalizedMessage());
        } catch (Exception e) {
            throw ApiExceptionFactory.genericError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }


    private HttpHeaders createHeaders(Map<String, List<String>> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, List<String>> header : headers.entrySet()) {
                httpHeaders.addAll(header.getKey(), header.getValue());
            }
        } else {
            httpHeaders.add("Content-Type", "application/json");
        }
        return httpHeaders;
    }
}
