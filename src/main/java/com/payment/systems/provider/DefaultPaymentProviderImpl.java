package com.payment.systems.provider;

import com.payment.systems.PaymentRequest;
import com.payment.systems.PaymentResponse;
import com.payment.systems.PaymentService;
import com.payment.systems.provider.paypal.PayPalPaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultPaymentProviderImpl implements PaymentProvider<PaymentService<PaymentRequest, PaymentResponse>> {
    private final ApplicationContext applicationContext;

    @Override
    public PaymentService getProvider(PaymentProviderType type) {
        switch (type) {
            case PAYPAL:
                return applicationContext.getBean(PayPalPaymentServiceImpl.class);

            default:
                throw new UnsupportedOperationException("Notification type: " + type + " not supported");
        }
    }

}