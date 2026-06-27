package com.program.razorpay.payment.gateway;

import com.program.razorpay.payment.gateway.dto.PaymentRequest;
import com.program.razorpay.payment.gateway.dto.PaymentResult;

public interface PaymentAdapter {

    PaymentResult initiate(PaymentRequest request);
}
