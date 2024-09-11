package ru.clevertec.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentDto;
import ru.clevertec.service.PaymentProvider;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentProviderFactory {
    private final Map<String, PaymentProvider> paymentProviderMap;

    @Autowired
    public PaymentProviderFactory(List<PaymentProvider> paymentProviders) {
        paymentProviderMap = paymentProviders
                .stream()
                .collect(Collectors.toMap(PaymentProvider::getProviderName, Function.identity()));
    }

    public PaymentProvider getSuitablePaymentProvider(PaymentDto paymentDto){
        return Optional.ofNullable(paymentProviderMap.get(paymentDto.getPaymentTitle()))
                .orElseThrow(RuntimeException::new);
    }
}
