package ru.clevertec.service.providers.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.observers.PaymentObserver;
import ru.clevertec.service.providers.AbstractPaymentProvider;

import java.util.List;

@Component
public class QiwiPaymentProvider extends AbstractPaymentProvider {

    public QiwiPaymentProvider(ByTelephoneNumberPaymentProvider paymentProvider, List<PaymentObserver> observers) {
        super(paymentProvider, observers);
    }

    @Override
    protected boolean canHandle(PaymentRequest request) {
        return request.getPaymentTitle().equalsIgnoreCase(TypesOfPayment.QIWI.getTypeTitle());
    }

    @Override
    protected void doHandle(PaymentRequest request) {
        System.out.println("Qiwi + " + request);
    }
}
