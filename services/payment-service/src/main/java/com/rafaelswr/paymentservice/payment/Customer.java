package com.rafaelswr.paymentservice.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "firstName is required")
        String firstName,
        @NotNull(message = "lastName is required")
        String lastName,

        @Email(message = "THe customer is not correctly formatted")
        @NotNull(message = "email is required")
        String email

) {
}
