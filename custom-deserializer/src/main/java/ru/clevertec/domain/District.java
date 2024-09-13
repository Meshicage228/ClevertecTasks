package ru.clevertec.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ru.clevertec.annotation.JsonField;

import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
    @JsonProperty(value = "districtStreets")
    private ArrayList<Street> streets;
    private ArrayList<Hospital> hospitals;
}
