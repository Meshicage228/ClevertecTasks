package ru.clevertec.exception;

public class InvalidJsonException extends AbstractException{
    private final static String message = "Invalid JSON!";

    public InvalidJsonException() {
        super(message);
    }
}
