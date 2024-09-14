package ru.clevertec.service.providers;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.PaymentRequest;
import ru.clevertec.service.observers.PaymentObserver;
import java.util.List;

@Component
@Getter
public abstract class AbstractPaymentProvider implements PaymentProvider{
    private final AbstractPaymentProvider nextProvider;
    private final List<PaymentObserver> observers;

    public AbstractPaymentProvider(AbstractPaymentProvider nextHandler, List<PaymentObserver> paymentObservers) {
        this.nextProvider = nextHandler;
        observers = paymentObservers;
    }

    public void handlePayment(PaymentRequest request) {
        if (canHandle(request)) {
            doHandle(request);
            notifyObservers(request);
        } else if (nextProvider != null) {
            nextProvider.handlePayment(request);
        }
    }

    protected abstract boolean canHandle(PaymentRequest request);
    protected abstract void doHandle(PaymentRequest request);

    public void addObserver(PaymentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PaymentObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(PaymentRequest request) {
        for (PaymentObserver observer : observers) {
            observer.update(request);
        }
    }
}
