package com.payment.systems.provider.payu;

import com.payment.systems.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class ProviderTwoPaymentServiceImpl implements PaymentService<PayUPaymentRequest, PayUPaymentResponse> {

    @Override
    public PayUPaymentResponse pay(PayUPaymentRequest request) {
        return new PayUPaymentResponse();
    }

}