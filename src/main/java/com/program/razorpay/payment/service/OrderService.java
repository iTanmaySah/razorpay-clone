package com.program.razorpay.payment.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.program.razorpay.payment.dto.request.CreateOrderRequest;
import com.program.razorpay.payment.dto.response.OrderResponse;
import com.program.razorpay.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);

    OrderResponse getById(UUID merchantId, UUID orderId);

    OrderResponse cancel(UUID merchantId, UUID orderId);

    List<PaymentResponse>
}
