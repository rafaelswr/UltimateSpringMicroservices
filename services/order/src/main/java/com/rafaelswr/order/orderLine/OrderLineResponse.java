package com.rafaelswr.order.orderLine;


import lombok.Builder;

@Builder
public record OrderLineResponse(
        Long id,
        double quantity


) {
}
