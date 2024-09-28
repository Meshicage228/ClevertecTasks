package ru.clevertec.exception;

public class PaymentStrategyNotFoundException extends RuntimeException {
    public PaymentStrategyNotFoundException(String unsupportedPaymentTitle) {
        super("Suitable payment provider wasn't found! : " + unsupportedPaymentTitle);
    }
}
