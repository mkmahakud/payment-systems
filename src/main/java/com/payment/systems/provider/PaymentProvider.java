package com.payment.systems.provider;

public interface PaymentProvider<T> {
    T getProvider(PaymentProviderType  type);
}