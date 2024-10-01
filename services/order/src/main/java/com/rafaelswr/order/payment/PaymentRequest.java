package com.rafaelswr.order.payment;

import com.rafaelswr.order.customer.CustomerResponse;
import com.rafaelswr.order.order.PaymentMethod;
import com.rafaelswr.order.order.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        @NotBlank
        PaymentMethod paymentMethod,
        BigDecimal amount,
        @NotBlank(message = "Should be not blank")
        Long orderId,
        String orderReference,
        @NotBlank
        CustomerResponse customer


) {
}
