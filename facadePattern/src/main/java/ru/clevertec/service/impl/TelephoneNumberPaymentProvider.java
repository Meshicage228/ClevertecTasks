package ru.clevertec.service.impl;

import org.springframework.stereotype.Service;
import ru.clevertec.enums.PaymentType;
import ru.clevertec.service.PaymentProvider;

@Service
public class TelephoneNumberPaymentProvider implements PaymentProvider {
    @Override
    public String paymentProcess(String message, Double check) {
        return message + " by " + getProviderName() + " check : " + check;
    }

    @Override
    public String getProviderName() {
        return PaymentType.TELEPHONE_NUMBER.getTypeTitle();
    }
}
