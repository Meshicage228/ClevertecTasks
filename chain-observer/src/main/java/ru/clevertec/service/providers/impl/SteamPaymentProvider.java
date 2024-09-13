package ru.clevertec.service.providers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.providers.AbstractPaymentProvider;

public class SteamPaymentProvider extends AbstractPaymentProvider {

    @Autowired
    public SteamPaymentProvider(AbstractPaymentProvider paymentProvider) {
        super(paymentProvider);
    }

    @Override
    protected boolean canHandle(PaymentRequest request) {
        return request.getPaymentTitle().equalsIgnoreCase(TypesOfPayment.STEAM.getTypeTitle());
    }

    @Override
    protected void doHandle(PaymentRequest request) {
        System.out.println("Steam + " + request);
    }
}
