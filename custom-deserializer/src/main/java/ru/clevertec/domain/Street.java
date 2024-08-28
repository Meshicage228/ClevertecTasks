package ru.clevertec.domain;

import lombok.*;
import ru.clevertec.annotation.JsonField;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Street {
    @JsonField(jsonField = "streetName")
    private String name;
    private boolean sleepingStreet;
}
