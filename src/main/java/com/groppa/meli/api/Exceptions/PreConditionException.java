package com.groppa.meli.api.Exceptions;

import org.springframework.http.HttpStatus;

public class PreConditionException extends CustomException {
    public PreConditionException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getErrorCode() {
        return HttpStatus.PRECONDITION_FAILED;
    }
}
