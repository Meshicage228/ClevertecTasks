package ru.clevertec.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.PaymentDto;
import ru.clevertec.service.PaymentProvider;
import ru.clevertec.service.TaxesService;
import ru.clevertec.util.PaymentStrategy;

@Service
@RequiredArgsConstructor
public class PaymentProviderFacade {
    private final PaymentStrategy paymentStrategy;
    private final TaxesService taxesService;

    public String paymentProcess(PaymentDto paymentDto){
        PaymentProvider paymentProvider = paymentStrategy.getSuitablePaymentProvider(paymentDto);

        Double totalCheck = taxesService.addTaxes(paymentDto.getIncomeMoney());

        return paymentProvider.paymentProcess("payment", totalCheck);
    }
}
