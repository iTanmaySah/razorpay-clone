package com.program.razorpay.payment.processor;

import com.program.razorpay.common.enums.PaymentMethod;
import com.program.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.program.razorpay.payment.processor.dto.PaymentProcessorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PaymentProcessorRouter {

    private Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public PaymentProcessorResponse charge(PaymentProcessorRequest request){

        PaymentProcessor processor = paymentProcessors.get(request.method());

        if(processor == null)
            throw new IllegalArgumentException("No payment processor registered for method: " + request.method());

        return processor.charge(request);
    }
}
