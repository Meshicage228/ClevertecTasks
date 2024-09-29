package ru.clevertec.service.providers;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.service.observers.PaymentObserver;
import java.util.List;

@Component
@Getter
public abstract class AbstractPaymentProvider implements PaymentProvider{
    private final PaymentProvider nextProvider;
    private final List<PaymentObserver> observers;

    public AbstractPaymentProvider(PaymentProvider nextHandler, List<PaymentObserver> paymentObservers) {
        this.nextProvider = nextHandler;
        observers = paymentObservers;
    }

    @Override
    public void handlePayment(PaymentRequest request) {
        if (canHandle(request)) {
            handleProviderPayment(request);
            notifyObservers(request);
        } else if (nextProvider != null) {
            nextProvider.handlePayment(request);
        }
    }

    protected abstract boolean canHandle(PaymentRequest request);
    protected abstract void handleProviderPayment(PaymentRequest request);

    public void addObserver(PaymentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PaymentObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(PaymentRequest request) {
        observers.forEach(paymentObserver -> paymentObserver.update(request));
    }
}
