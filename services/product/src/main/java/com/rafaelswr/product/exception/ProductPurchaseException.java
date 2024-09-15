package com.rafaelswr.product.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductPurchaseException extends RuntimeException{

    private final String message;

    public ProductPurchaseException(String message) {
        this.message = message;
    }
}
