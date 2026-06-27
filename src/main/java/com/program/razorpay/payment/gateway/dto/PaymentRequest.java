package com.program.razorpay.payment.gateway.dto;

import com.program.razorpay.common.entity.Money;
import com.program.razorpay.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentRequest(
        UUID paymentId,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentMethod method,
        Map<String, Object> methodsDetails
) {
}
