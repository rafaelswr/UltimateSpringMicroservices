package com.rafaelswr.notification.kafka.order;


import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
