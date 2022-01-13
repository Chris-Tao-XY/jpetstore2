package com.txy.jpetstore.demo.exception;

public class UpdateWrongException extends RuntimeException{
    public UpdateWrongException() {
    }

    public UpdateWrongException(String message) {
        super(message);
    }
}
