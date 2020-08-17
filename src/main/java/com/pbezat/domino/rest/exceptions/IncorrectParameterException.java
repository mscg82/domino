package com.pbezat.domino.rest.exceptions;

public class IncorrectParameterException extends RuntimeException {
    public IncorrectParameterException(final String message) {
        super(message);
    }
}
