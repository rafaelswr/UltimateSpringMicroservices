package com.rafaelswr.notification.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation (

        String customerFirstName,
        String customerLastName,
        String customerEmail,
        Status paymentStatus,
        BigDecimal total,
        String orderReference,
        PaymentMethod paymentMethod

    ){

}
