package ru.clevertec.enums;

import lombok.Getter;

@Getter
public enum ConstantChars {
    FIGURE_OPENED('{'),
    FIGURE_CLOSED('}'),
    SQUARE_OPENED('['),
    SQUARE_CLOSED(']'),
    SPEC_DELIMITER('|'),
    DELIMITER_OBJECTS('&'),
    COLON(':'),
    COMMA(','),
    NEW_LINE('\n');

    private final char symbol;

    ConstantChars(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
