package com.authenticate.belajar.models;

public class ErrorResponse {
    private String message;
    private String httpStatus;

    public void setMessage(String message){
        this.message = message;
    }

    public void setHttpStatus(String httpStatus){
        this.httpStatus = httpStatus;
    }
}
