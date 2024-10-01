package com.rafaelswr.paymentservice.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(PaymentRequest paymentRequest){
        return Payment.builder()
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .id(paymentRequest.id())
                .reference(paymentRequest.orderReference())
                .build();
    }
}
