package com.rafaelswr.order.orderLine;

import com.rafaelswr.order.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest){
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .product_id(orderLineRequest.productId())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .build();
    }

}
