package ru.clevertec.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentDto;
import ru.clevertec.service.PaymentProvider;
import java.util.Map;
import java.util.Optional;

@Component
public class PaymentStrategy {
    private final Map<String, PaymentProvider> paymentProvides;

    @Autowired
    public PaymentStrategy(@Qualifier("paymentProvides") Map<String, PaymentProvider> paymentProvides) {
        this.paymentProvides = paymentProvides;
    }

    public PaymentProvider getSuitablePaymentProvider(PaymentDto paymentDto){
        return Optional.ofNullable(paymentProvides.get(paymentDto.getPaymentTitle()))
                .orElseThrow(RuntimeException::new);
    }
}
