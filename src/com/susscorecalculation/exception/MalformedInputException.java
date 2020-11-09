package com.susscorecalculation.exception;

public class MalformedInputException extends Exception{
    public MalformedInputException() {
    }

    public MalformedInputException(String message) {
        super(message);
    }

    public MalformedInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedInputException(Throwable cause) {
        super(cause);
    }

    public MalformedInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
