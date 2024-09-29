package ru.clevertec.service;

public interface PaymentProvider {
    String paymentProcess(String message, Double check);
    String getProviderName();
}
