package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.clevertec.dto.PaymentDto;
import ru.clevertec.facade.PaymentProviderFacade;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentProviderFacade providerFacade;

    @PostMapping("/processPayment")
    public void paymentProcess(@RequestBody PaymentDto paymentDto){
        System.out.println(providerFacade.paymentProcess(paymentDto));
    }
}
