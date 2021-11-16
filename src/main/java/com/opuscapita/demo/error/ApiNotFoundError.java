package com.opuscapita.demo.error;

public class ApiNotFoundError extends RuntimeException {

    public ApiNotFoundError(String s) {
        super(s);
    }
}
