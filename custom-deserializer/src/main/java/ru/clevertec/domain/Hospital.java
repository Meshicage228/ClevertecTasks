package ru.clevertec.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hospital {
    private String title;
    private Integer yearOfFoundation;
}
