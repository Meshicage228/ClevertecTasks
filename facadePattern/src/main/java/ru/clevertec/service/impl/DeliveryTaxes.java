package ru.clevertec.service.impl;

import org.springframework.stereotype.Service;
import ru.clevertec.service.TaxesService;

@Service
public class DeliveryTaxes implements TaxesService {
    @Override
    public Double addTaxes(Double incomeMoney) {
        incomeMoney += incomeMoney * 0.2d;
        return incomeMoney;
    }
}
