package com.program.razorpay.payment.service;

import com.program.razorpay.payment.dto.request.CreateOrderRequest;
import com.program.razorpay.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
