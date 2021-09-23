package com.groppa.meli.api.exceptions;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends Exception {
    public CustomException(final String message) {
        super(message);
    }

    public abstract HttpStatus getErrorCode();
}
