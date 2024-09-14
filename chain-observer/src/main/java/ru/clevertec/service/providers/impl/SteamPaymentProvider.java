package ru.clevertec.service.providers.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.observers.PaymentObserver;
import ru.clevertec.service.providers.AbstractPaymentProvider;

import java.util.List;

@Component("PaymentStartChain")
public class SteamPaymentProvider extends AbstractPaymentProvider {

    public SteamPaymentProvider(QiwiPaymentProvider paymentProvider, List<PaymentObserver> paymentObserverList) {
        super(paymentProvider, paymentObserverList);
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
