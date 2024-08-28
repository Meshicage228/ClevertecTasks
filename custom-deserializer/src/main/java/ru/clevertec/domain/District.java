package ru.clevertec.domain;

import lombok.*;
import ru.clevertec.annotation.JsonField;

import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class District {
    private String districtName;
    private Integer schoolCount;
    private Street mainStreet;
    private Hospital hospital;
    @JsonField(jsonField = "districtStreets")
    private ArrayList<Street> streets;
    private ArrayList<Hospital> hospitals;
}
