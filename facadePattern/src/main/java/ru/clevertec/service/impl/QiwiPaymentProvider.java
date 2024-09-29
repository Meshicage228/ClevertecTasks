package ru.clevertec.service.impl;

import org.springframework.stereotype.Service;
import ru.clevertec.enums.PaymentType;
import ru.clevertec.service.PaymentProvider;

@Service
public class QiwiPaymentProvider implements PaymentProvider {
    @Override
    public String paymentProcess(String message, Double check) {
        return message + " by " + getProviderName() + " check : " + check;
    }

    @Override
    public String getProviderName() {
        return PaymentType.QIWI.getTypeTitle();
    }
}
