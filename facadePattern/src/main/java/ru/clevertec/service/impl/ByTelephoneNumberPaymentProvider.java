package ru.clevertec.service.impl;

import org.springframework.stereotype.Service;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.PaymentProvider;

@Service
public class ByTelephoneNumberPaymentProvider implements PaymentProvider {
    @Override
    public String paymentProcess(String message, Double check) {
        return message + " by " + getProviderName() + " check : " + check;
    }

    @Override
    public String getProviderName() {
        return TypesOfPayment.TELEPHONE_NUMBER.getTypeTitle();
    }
}
