package com.rafaelswr.product.product;

import lombok.Builder;

@Builder
public record CategoryDTO(
        String name,
        String description
) {
}
