package com.program.razorpay.payment.processor.strategy;

import com.program.razorpay.payment.gateway.dto.PaymentRequest;
import com.program.razorpay.payment.processor.PaymentProcessor;
import com.program.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.program.razorpay.payment.processor.dto.PaymentProcessorResponse;

public class NetBankingPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        return null;
    }
}
