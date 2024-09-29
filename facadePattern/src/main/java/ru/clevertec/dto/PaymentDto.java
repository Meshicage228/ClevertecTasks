package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
public class PaymentDto {
    private String paymentTitle;
    private Double incomeMoney;
}
