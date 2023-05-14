package com.xwsBooking.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
class BaseException extends RuntimeException {

    protected HttpStatus statusCode;

    public BaseException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public BaseException(String message, HttpStatus statusCode, Throwable e) {
        super(message, e);
        this.statusCode = statusCode;
    }
}