package com.groppa.meli.api.controller;

import com.groppa.meli.api.exceptions.CustomException;
import com.groppa.meli.api.model.Error;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Log4j2
public class BaseController {
    protected ResponseEntity buildResponseError(Exception ex) {
        log.info("Starting method buildResponseError");
        final Error error = Error.builder().message(ex.getMessage()).build();

        final HttpStatus status = (ex instanceof CustomException ? ((CustomException) ex).getErrorCode() : HttpStatus.INTERNAL_SERVER_ERROR);

        error.setStatus(status.value());

        log.info("Status code: " + error.getStatus());
        log.info("Error: " + ex.getMessage());

        log.info("Finishing method buildResponseError");
        return new ResponseEntity<>(error, status);
    }
}
