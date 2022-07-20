package com.payment.systems.provider;

import com.payment.systems.PaymentRequest;
import com.payment.systems.PaymentResponse;
import com.payment.systems.PaymentService;
import com.payment.systems.provider.paypal.PayPalPaymentServiceImpl;
import com.payment.systems.provider.paytm.PaytmPaymentServiceImpl;
import com.payment.systems.provider.payu.PayUPaymentServiceImpl;
import com.payment.systems.provider.stripe.StripePaymentServiceImpl;
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
            case PAYTM:
                return applicationContext.getBean(PaytmPaymentServiceImpl.class);
            case PAYU:
                return applicationContext.getBean(PayUPaymentServiceImpl.class);
            case STRIPE:
                return applicationContext.getBean(StripePaymentServiceImpl.class);

            default:
                throw new UnsupportedOperationException("Notification type: " + type + " not supported");
        }
    }

}