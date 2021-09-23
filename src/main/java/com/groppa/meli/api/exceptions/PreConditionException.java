package com.groppa.meli.api.exceptions;

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
