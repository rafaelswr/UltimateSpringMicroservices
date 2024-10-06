package com.rafaelswr.order.order;


import com.rafaelswr.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderRequest(

        Long id,
        @NotBlank
        String reference,
        @Positive(message = "Order Amount should be positive")
        BigDecimal totalAmount,
        @NotNull(message = "Payment method should not be assigned")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer should be referenced")
        @NotEmpty(message = "Customer should be referenced")
        @NotBlank(message = "Customer should be referenced")
        String customerId,

        @NotEmpty(message = "Empty! You should at least purchase one product")
        List<PurchaseRequest> products

) {
}
