package com.ank.rkr.exception;

public class TransientException extends RuntimeException{

    public TransientException() {
        super();
    }

    public TransientException(String message) {
        super(message);
    }

    public TransientException(Throwable cause) {
        super(cause);
    }

    public TransientException(String message, Throwable cause) {
        super(message, cause);
    }

}
