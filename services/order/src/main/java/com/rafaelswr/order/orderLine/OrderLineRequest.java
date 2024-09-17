package com.rafaelswr.order.orderLine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        double quantity
) {

}
