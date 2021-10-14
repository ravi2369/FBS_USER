package com.fbs.user.exceptions;

public class InvalidDataException extends Exception {
    public InvalidDataException(String string) {
        super(string);
    }

    public InvalidDataException() {
    }
}
