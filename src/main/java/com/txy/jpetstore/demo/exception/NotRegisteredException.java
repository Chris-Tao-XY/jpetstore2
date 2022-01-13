package com.txy.jpetstore.demo.exception;

public class NotRegisteredException extends RuntimeException{
    public NotRegisteredException(String message) {
        super(message);
    }

    public NotRegisteredException() {
    }
}
