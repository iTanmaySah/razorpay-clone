package com.program.razorpay.payment.service;

import com.program.razorpay.payment.dto.request.PaymentInitRequest;
import com.program.razorpay.payment.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);
}
