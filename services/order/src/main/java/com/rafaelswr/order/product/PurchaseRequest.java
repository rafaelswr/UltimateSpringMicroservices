package com.rafaelswr.order.product;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record PurchaseRequest (
    @NotNull(message = "product is mandatory")
    Long product_id,

    @Positive(message = "Quantity is mandatory")
    double quantity
){

}
