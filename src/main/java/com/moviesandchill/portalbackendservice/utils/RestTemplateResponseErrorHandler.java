package com.moviesandchill.portalbackendservice.utils;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) {

    }
}
