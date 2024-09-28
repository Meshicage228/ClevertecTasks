package ru.clevertec.service.providers.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.observers.PaymentObserver;
import ru.clevertec.service.providers.AbstractPaymentProvider;
import ru.clevertec.service.providers.PaymentProvider;

import java.util.List;

@Component("qiwiPaymentProvider")
public class QiwiPaymentProvider extends AbstractPaymentProvider {

    public QiwiPaymentProvider(@Qualifier("telephonePaymentProvider") PaymentProvider nextPaymentProvider, List<PaymentObserver> observers) {
        super(nextPaymentProvider, observers);
    }

    @Override
    protected boolean canHandle(PaymentRequest request) {
        return request.getPaymentTitle().equalsIgnoreCase(TypesOfPayment.QIWI.getTypeTitle());
    }

    @Override
    protected void handleProviderPayment(PaymentRequest request) {
        System.out.println("Qiwi + " + request);
    }
}
