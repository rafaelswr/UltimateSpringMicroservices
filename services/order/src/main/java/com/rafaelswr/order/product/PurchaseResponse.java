package com.rafaelswr.order.product;

import java.math.BigDecimal;

public record PurchaseResponse(

        Long product_id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity

) {
}
