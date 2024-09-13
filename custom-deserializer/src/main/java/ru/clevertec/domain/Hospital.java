package ru.clevertec.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@Builder
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hospital {
    private String title;
    private Integer yearOfFoundation;
}
