package ru.clevertec.exception;

public class KeyMismatchException extends RuntimeException{
    private final static String message = "Non-existent key was found : ";

    public KeyMismatchException(String key) {
        super(message + key);
    }
}
