package com.program.razorpay.payment.processor;

import com.program.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.program.razorpay.payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);
}
