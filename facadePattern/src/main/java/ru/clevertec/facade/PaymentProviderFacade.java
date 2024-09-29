package ru.clevertec.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.PaymentDto;
import ru.clevertec.exception.PaymentStrategyNotFoundException;
import ru.clevertec.service.PaymentProvider;
import ru.clevertec.service.TaxesService;

import java.util.Map;
import java.util.Optional;

@Service
public class PaymentProviderFacade {
    private final Map<String, PaymentProvider> paymentProvides;
    private final TaxesService taxesService;

    @Autowired
    public PaymentProviderFacade(@Qualifier("paymentProvides") Map<String, PaymentProvider> paymentProvides, TaxesService taxesService) {
        this.paymentProvides = paymentProvides;
        this.taxesService = taxesService;
    }

    public String paymentProcess(PaymentDto paymentDto){
        return Optional.ofNullable(paymentProvides.get(paymentDto.getPaymentTitle()))
                .map(paymentProvider -> {
                    Double totalCheck = taxesService.addTaxes(paymentDto.getIncomeMoney());
                    return paymentProvider.paymentProcess("payment", totalCheck);
                })
                .orElseThrow(() -> new PaymentStrategyNotFoundException(paymentDto.getPaymentTitle()));
    }
}
