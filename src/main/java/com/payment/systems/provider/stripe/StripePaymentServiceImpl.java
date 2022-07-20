package com.payment.systems.provider.stripe;

import com.payment.systems.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentServiceImpl implements PaymentService<StripePaymentRequest, StripePaymentResponse> {

    @Override
    public StripePaymentResponse pay(StripePaymentRequest request) {
        return new StripePaymentResponse();
    }

}