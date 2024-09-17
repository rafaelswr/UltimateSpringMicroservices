package com.rafaelswr.product.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class ProductPurchaseException extends RuntimeException{

    private final String message;

    public ProductPurchaseException(String message) {
        this.message = message;
    }
}
