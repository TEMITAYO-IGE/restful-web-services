package com.osagie.restfulwebservices.exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private  String detail;

    public ExceptionResponse(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
