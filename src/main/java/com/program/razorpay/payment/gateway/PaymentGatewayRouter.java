package com.program.razorpay.payment.gateway;

import com.program.razorpay.common.enums.PaymentMethod;
import com.program.razorpay.payment.gateway.dto.PaymentRequest;
import com.program.razorpay.payment.gateway.dto.PaymentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {
//this gets from paymentadapterconfig and then using that map it initiates object and tells which adapter class to go to.
    private final Map<PaymentMethod, PaymentAdapter> paymentAdapters;

    public PaymentResult initiate(PaymentRequest request){
        //parent class reference, child class object. parent paymentadapter interfact and child particular payment adapter classes.
        PaymentAdapter adapter = paymentAdapters.get(request.method());

        if(adapter == null)
            throw new IllegalArgumentException("No payment adapter registered for method: " + request.method());

        //initiate particular child's method
        return adapter.initiate(request);
    }
}
