package ru.clevertec.service.providers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.enums.TypesOfPayment;
import ru.clevertec.service.providers.AbstractPaymentProvider;

public class ByTelephoneNumberPaymentProvider extends AbstractPaymentProvider {

    @Autowired
    public ByTelephoneNumberPaymentProvider(AbstractPaymentProvider paymentProvider) {
        super(paymentProvider);
    }

    @Override
    protected boolean canHandle(PaymentRequest request) {
        return request.getPaymentTitle().equalsIgnoreCase(TypesOfPayment.TELEPHONE_NUMBER.getTypeTitle());
    }

    @Override
    protected void doHandle(PaymentRequest request) {
        System.out.println("Telephone + " + request);
    }
}
