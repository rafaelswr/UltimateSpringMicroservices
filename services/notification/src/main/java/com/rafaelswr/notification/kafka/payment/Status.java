package com.rafaelswr.notification.kafka.payment;

public enum Status {
    AWAITING_PAYMENT,
    PAYMENT_RECEIVED,
    CANCELLED_PAYMENT,
    IN_PROGRESS,
    SENT,
    DELIVERED
}