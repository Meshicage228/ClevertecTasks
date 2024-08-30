package ru.clevertec.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ru.clevertec.annotation.JsonField;

@Getter
@Setter
@ToString
@Builder
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Street {
    @JsonField(jsonField = "streetName")
    @JsonProperty(value = "streetName")
    private String name;
    private boolean sleepingStreet;

    public boolean getSleepingStreet() {
        return sleepingStreet;
    }
}
