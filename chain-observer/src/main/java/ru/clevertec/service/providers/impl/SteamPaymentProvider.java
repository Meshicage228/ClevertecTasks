package ru.clevertec.service.providers.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.observers.PaymentObserver;
import ru.clevertec.service.providers.AbstractPaymentProvider;
import ru.clevertec.service.providers.PaymentProvider;

import java.util.List;

@Component("paymentStartChain")
public class SteamPaymentProvider extends AbstractPaymentProvider {

    public SteamPaymentProvider(@Qualifier("qiwiPaymentProvider") PaymentProvider nextPaymentProvider, List<PaymentObserver> paymentObserverList) {
        super(nextPaymentProvider, paymentObserverList);
    }

    @Override
    protected boolean canHandle(PaymentRequest request) {
        return request.getPaymentTitle().equalsIgnoreCase(TypesOfPayment.STEAM.getTypeTitle());
    }

    @Override
    protected void handleProviderPayment(PaymentRequest request) {
        System.out.println("Steam + " + request);
    }
}
