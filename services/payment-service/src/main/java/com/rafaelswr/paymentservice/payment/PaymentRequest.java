package com.rafaelswr.paymentservice.payment;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(

        Long id,
        PaymentMethod paymentMethod,
        BigDecimal amount,

        @NotBlank
        Long orderId,
        String orderReference,
        Customer customer
) {
}
