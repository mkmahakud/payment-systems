package com.payment.systems.provider.paypal;

import com.payment.systems.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PayPalPaymentServiceImpl implements PaymentService<PayPalPaymentRequest, PayPalPaymentResponse> {

    @Override
    public PayPalPaymentResponse pay(PayPalPaymentRequest request) {
        return new PayPalPaymentResponse();
    }

}