package com.program.razorpay.payment.gateway.dto;

import com.program.razorpay.payment.entity.Payment;

public sealed interface PaymentResult permits PaymentResult.Pending, PaymentResult.Failure{

    record Pending(String registrationRef) implements PaymentResult{}

    record Failure(String errorCode, String errorDescription) implements PaymentResult{}

}
