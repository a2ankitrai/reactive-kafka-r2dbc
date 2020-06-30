package com.ank.rkr.exception;

public class NonTransientException extends RuntimeException{

    public NonTransientException() {
        super();
    }

    public NonTransientException(String message) {
        super(message);
    }

    public NonTransientException(Throwable cause) {
        super(cause);
    }

    public NonTransientException(String message, Throwable cause) {
        super(message, cause);
    }

}
