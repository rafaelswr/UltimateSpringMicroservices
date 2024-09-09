package com.rafaelswr.customer.customer;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "customers")
@Builder
public class Customer {

    @Id
    private String id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotEmpty
    private Address address;

    @Digits(integer = 10, fraction = 0)
    private BigDecimal phone;

}
