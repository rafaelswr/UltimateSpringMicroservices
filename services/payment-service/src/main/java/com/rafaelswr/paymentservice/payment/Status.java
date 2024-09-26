package com.rafaelswr.paymentservice.payment;

public enum Status {
    AWAITING_PAYMENT,
    PAYMENT_RECEIVED,
    CANCELLED_PAYMENT,
    IN_PROGRESS,
    SENT,
    DELIVERED
}