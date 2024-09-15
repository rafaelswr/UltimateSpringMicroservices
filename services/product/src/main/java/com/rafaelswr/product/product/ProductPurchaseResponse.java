package com.rafaelswr.product.product;

import com.rafaelswr.product.service.ProductService;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductPurchaseResponse(

        Long productId,
        String name,
        String description,
        BigDecimal price,
        Integer requestQuantity

) {
}
