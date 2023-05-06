package com.xwsBooking.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardErrorResponse {
    private String message;
    private int statusCode;


    public StandardErrorResponse(BaseException exception) {
        this.message = exception.getMessage();
        this.statusCode = exception.getStatusCode().value();
    }
}