package com.payment.systems;

public interface PaymentService<T extends PaymentRequest, R extends PaymentResponse> {
        R pay(T request);
}
