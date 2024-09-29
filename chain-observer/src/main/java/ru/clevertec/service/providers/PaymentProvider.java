package ru.clevertec.service.providers;

import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.service.observers.PaymentObserver;

public interface PaymentProvider {
    void handlePayment(PaymentRequest request);
    void addObserver(PaymentObserver observer);
    void removeObserver(PaymentObserver observer);
}
