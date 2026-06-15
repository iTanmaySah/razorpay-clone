package com.program.razorpay.common.enums;

public enum PaymentStatus {
    CREATED,
    AUTHORIZING,
    CAPTURING,
    CAPTURED,
    FAILED,
    CANCELLED,
    REFUNDED,
    PARTIALLY_REFUNDED,
    SETTLED,
    AUTH_EXPIRED
}
