package com.program.razorpay.payment.gateway.adapter;

import com.program.razorpay.payment.gateway.PaymentAdapter;
import com.program.razorpay.payment.gateway.dto.PaymentRequest;
import com.program.razorpay.payment.gateway.dto.PaymentResult;

public class CardPaymentAdapter implements PaymentAdapter {
//these particular adapters will go ahead and call other third parties to initiate the payment (or maybe personal payment processor)
    public PaymentResult initiate(PaymentRequest request){
        return null;
    }
}
