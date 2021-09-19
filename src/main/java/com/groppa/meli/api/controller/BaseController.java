package com.groppa.meli.api.controller;

import com.groppa.meli.api.Exceptions.CustomException;
import com.groppa.meli.api.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity buildResponseError(Exception ex) {
        final Error error = Error.builder().message(ex.getMessage()).build();

        final HttpStatus status = (ex instanceof CustomException ? ((CustomException) ex).getErrorCode() : HttpStatus.INTERNAL_SERVER_ERROR);

        error.setStatus(status.value());

        return new ResponseEntity<>(error, status);
    }
}
