package com.rafaelswr.notification.kafka.order;

import com.rafaelswr.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

        /**
         *
         * String reference,
         *         BigDecimal totalAmount,
         *         PaymentMethod paymentMethod,
         *         CustomerResponse customerResponse,
         *         List<PurchaseResponse> products
         */


)
{

}
