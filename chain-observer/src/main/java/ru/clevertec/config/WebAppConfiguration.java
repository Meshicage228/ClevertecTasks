package ru.clevertec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.clevertec.service.observers.PaymentObserver;
import ru.clevertec.service.providers.AbstractPaymentProvider;
import ru.clevertec.service.providers.PaymentProvider;
import ru.clevertec.service.providers.impl.ByTelephoneNumberPaymentProvider;
import ru.clevertec.service.providers.impl.QiwiPaymentProvider;
import ru.clevertec.service.providers.impl.SteamPaymentProvider;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "ru.clevertec")
@EnableWebMvc
public class WebAppConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    @Bean(name = "PaymentChain")
    public PaymentProvider paymentHandler(List<PaymentObserver> paymentObservers) {
        AbstractPaymentProvider telephoneNumberPaymentProvider = new ByTelephoneNumberPaymentProvider(null);
        AbstractPaymentProvider steamPaymentProvider = new SteamPaymentProvider(telephoneNumberPaymentProvider);
        AbstractPaymentProvider qiwiPaymentProvider = new QiwiPaymentProvider(steamPaymentProvider);

        addObserversToHandlerChain(qiwiPaymentProvider, paymentObservers);
        return qiwiPaymentProvider;
    }

    private void addObserversToHandlerChain(AbstractPaymentProvider paymentProvider, List<PaymentObserver> observers) {
        observers.forEach(paymentProvider::addObserver);
        if (paymentProvider.getNextProvider() != null) {
            addObserversToHandlerChain(paymentProvider.getNextProvider(), observers);
        }
    }
}
