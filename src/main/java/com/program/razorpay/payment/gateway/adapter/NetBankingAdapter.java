package com.program.razorpay.payment.gateway.adapter;

import com.program.razorpay.payment.gateway.PaymentAdapter;
import com.program.razorpay.payment.gateway.dto.PaymentRequest;
import com.program.razorpay.payment.gateway.dto.PaymentResult;

public class NetBankingAdapter implements PaymentAdapter {

    @Override
    public PaymentResult initiate(PaymentRequest request) {
        return null;
    }
}
