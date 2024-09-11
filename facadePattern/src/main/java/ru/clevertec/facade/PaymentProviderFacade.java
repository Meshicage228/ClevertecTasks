package ru.clevertec.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.PaymentDto;
import ru.clevertec.service.PaymentProvider;
import ru.clevertec.service.TaxesService;
import ru.clevertec.util.PaymentProviderFactory;

@Service
@RequiredArgsConstructor
public class PaymentProviderFacade {
    private final PaymentProviderFactory providerFactory;
    private final TaxesService taxesService;

    public String paymentProcess(PaymentDto paymentDto){
        PaymentProvider paymentProvider = providerFactory.getSuitablePaymentProvider(paymentDto);

        Double totalCheck = taxesService.addTaxes(paymentDto.getIncomeMoney());

        return paymentProvider.paymentProcess("payment", totalCheck);
    }
}
