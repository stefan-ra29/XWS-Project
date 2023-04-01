package com.xwsProject.FlightsBackend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends BaseException {

    public CustomBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public CustomBadRequestException(String message, Throwable e) {
        super(message, HttpStatus.BAD_REQUEST, e);
    }

}
