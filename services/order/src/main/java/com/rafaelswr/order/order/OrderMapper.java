package com.rafaelswr.order.order;

import com.rafaelswr.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest){
        return Order.builder()
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.totalAmount())
                .customerId(orderRequest.customerId())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }

    public OrderResponse toOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .amount(order.getTotalAmount())
                .dateHour(order.getCreated_at())
                .reference(order.getReference())
                .customerId(order.getCustomerId())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }


}
