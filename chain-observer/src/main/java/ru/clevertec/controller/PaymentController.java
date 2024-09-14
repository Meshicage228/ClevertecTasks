package ru.clevertec.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.service.providers.PaymentProvider;

@Controller
public class PaymentController {
    private final PaymentProvider paymentProvider;

    public PaymentController(@Qualifier("PaymentStartChain") PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    @PostMapping("/processPayment")
    public void paymentProcess(@RequestBody PaymentRequest paymentRequest){
        paymentProvider.handlePayment(paymentRequest);
    }
}
