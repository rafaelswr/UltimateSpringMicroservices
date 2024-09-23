package com.rafaelswr.order.kafka;

import com.rafaelswr.order.customer.CustomerResponse;
import com.rafaelswr.order.order.PaymentMethod;
import com.rafaelswr.order.product.PurchaseResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmation (
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
