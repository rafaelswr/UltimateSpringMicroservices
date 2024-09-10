package com.rafaelswr.customer.customer;

import lombok.Builder;

@Builder
public record CustomerResponseDTO(
        String firstName,
        String lastname,
        String email
) {
}
