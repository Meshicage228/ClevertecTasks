package ru.clevertec.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractException extends RuntimeException {
    private final String message;
}
