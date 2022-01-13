package com.txy.jpetstore.demo.exception;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException() {
    }

    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
