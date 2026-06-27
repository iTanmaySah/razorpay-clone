package com.program.razorpay.payment.service.impl;

import com.program.razorpay.common.enums.OrderStatus;
import com.program.razorpay.common.enums.PaymentStatus;
import com.program.razorpay.common.exception.BusinessRuleViolationException;
import com.program.razorpay.common.exception.ResourceNotFoundException;
import com.program.razorpay.payment.dto.request.PaymentInitRequest;
import com.program.razorpay.payment.dto.response.PaymentResponse;
import com.program.razorpay.payment.entity.OrderRecord;
import com.program.razorpay.payment.entity.Payment;
import com.program.razorpay.payment.gateway.PaymentGatewayRouter;
import com.program.razorpay.payment.gateway.dto.PaymentRequest;
import com.program.razorpay.payment.repository.OrderRepository;
import com.program.razorpay.payment.repository.PaymentRepository;
import com.program.razorpay.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;

    @Override
    public PaymentResponse initiate(UUID merchantId, PaymentInitRequest request) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(request.orderId(), merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", request.orderId()));

        if(order.getOrderStatus() != OrderStatus.CREATED && order.getOrderStatus() != OrderStatus.ATTEMPTED){
            throw new BusinessRuleViolationException("ORDER_NOT_PAYABLE",
                    "Order cannot accept payment in status: " + order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.ATTEMPTED);
        order.setAttempts(order.getAttempts() + 1);

        Payment payment = Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();

        payment = paymentRepository.save(payment);

        //created adapters to avoid switch case for different payment modes
        //created paymentgatewayrouter to handle which adapter to go

        PaymentRequest paymentRequest = new PaymentRequest(payment.getId(),
                request.orderId(),
                merchantId,
                order.getAmount(),
                request.method(),
                request.methodDetails());

        paymentGatewayRouter.initiate(paymentRequest);

        return null;
    }
}
