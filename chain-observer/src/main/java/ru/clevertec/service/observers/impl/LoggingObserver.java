package ru.clevertec.service.observers.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.service.observers.PaymentObserver;

@Component
public class LoggingObserver implements PaymentObserver {
    @Override
    public void update(PaymentRequest request) {
        System.out.println(request.getPaymentTitle() + " was handled");
    }
}
