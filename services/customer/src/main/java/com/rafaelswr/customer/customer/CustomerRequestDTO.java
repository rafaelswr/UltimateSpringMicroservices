package com.rafaelswr.customer.customer;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CustomerRequestDTO(

        String id,

        @NotNull
        String firstName,

        @NotNull
        String lastName,

        @NotNull
        @Email(message = "Invalid email format")
        String email,

        @NotNull
        Address address,

        @Digits(integer = 10, fraction = 0)
        BigDecimal phone


) {
}
