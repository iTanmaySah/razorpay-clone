package com.program.razorpay.payment.gateway.adapter;

import com.program.razorpay.payment.gateway.PaymentAdapter;
import com.program.razorpay.payment.gateway.dto.PaymentRequest;

public class CardPaymentAdapter implements PaymentAdapter {
//these particular adapters will go ahead and call other third parties to initiate the payment (or maybe personal payment processor)
    public void initiate(PaymentRequest request){

    }
}
