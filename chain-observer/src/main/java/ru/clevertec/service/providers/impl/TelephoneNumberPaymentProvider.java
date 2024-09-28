package ru.clevertec.service.providers.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.observers.PaymentObserver;
import ru.clevertec.service.providers.AbstractPaymentProvider;

import java.util.List;

@Component("telephonePaymentProvider")
public class TelephoneNumberPaymentProvider extends AbstractPaymentProvider {

    public TelephoneNumberPaymentProvider(List<PaymentObserver> observerList) {
        super(null, observerList);
    }

    @Override
    protected boolean canHandle(PaymentRequest request) {
        return request.getPaymentTitle().equalsIgnoreCase(TypesOfPayment.TELEPHONE_NUMBER.getTypeTitle());
    }

    @Override
    protected void handleProviderPayment(PaymentRequest request) {
        System.out.println("Telephone + " + request);
    }
}
