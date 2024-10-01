package com.rafaelswr.paymentservice.kafka;


import com.rafaelswr.paymentservice.payment.PaymentMethod;
import com.rafaelswr.paymentservice.payment.Status;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentNotification(

        String customerFirstName,
        String customerLastName,
        String customerEmail,
        Status paymentStatus,
        BigDecimal total,
        String orderReference,
        PaymentMethod paymentMethod



) {
}
