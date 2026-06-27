package com.program.razorpay.payment.processor.dto;

import com.program.razorpay.common.entity.Money;
import com.program.razorpay.common.enums.PaymentMethod;

import java.util.Map;
import java.util.UUID;

public record PaymentProcessorRequest(
        PaymentMethod method,
        Money amount,
        Map<String, Object> methodDetails
) {

}
