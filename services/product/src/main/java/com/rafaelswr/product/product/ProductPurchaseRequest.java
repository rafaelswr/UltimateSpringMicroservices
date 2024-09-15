package com.rafaelswr.product.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotBlank(message = "Product ID is mandatory")
        Long product_id,
        @NotNull(message = "Quantity is mandatory")
        Integer quantity
) { }
