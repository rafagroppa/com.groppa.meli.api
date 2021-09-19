package com.groppa.meli.api.Exceptions;

import org.springframework.http.HttpStatus;

public class ContentNotFoundException extends CustomException {

    public ContentNotFoundException(final String message) {
        super(message);
    }

    @Override
    public HttpStatus getErrorCode() {
        return HttpStatus.NOT_FOUND;
    }
}
