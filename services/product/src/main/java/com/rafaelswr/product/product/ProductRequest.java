package com.rafaelswr.product.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        @NotBlank(message = "Name product is required")
        String name,
        @NotNull(message = "Description is necessary")
        String description,
        @Positive(message = "price should be positive")
        BigDecimal price,
        @Positive(message = "Product stock should have more than 1 product")
        Integer availableQuantity,
        @NotBlank(message = "Product Category is required")
        Long categoryId
) {
}
