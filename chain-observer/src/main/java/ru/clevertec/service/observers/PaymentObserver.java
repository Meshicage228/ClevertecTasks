package ru.clevertec.service.observers;

import ru.clevertec.dto.PaymentRequest;

public interface PaymentObserver {
    void update(PaymentRequest request);
}
