package ru.clevertec.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType {
    STEAM ("Steam"),
    QIWI ("Qiwi"),
    TELEPHONE_NUMBER ("Telephone_number");

    private final String typeTitle;

}
