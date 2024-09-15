package com.rafaelswr.product.product;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        Integer availableQuantity,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
