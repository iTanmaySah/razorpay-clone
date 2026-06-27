package com.program.razorpay.payment.processor.dto;

//interface dto because we can have three types of payment response, pending successful, failure
public sealed interface PaymentProcessorResponse permits
        PaymentProcessorResponse.Pending,
        PaymentProcessorResponse.Success,
        PaymentProcessorResponse.Failure{

    record Pending(String processorReference) implements PaymentProcessorResponse{}

    record Success(String processorReference, String bankReference) implements PaymentProcessorResponse{}

    record Failure(String errorCode, String errorDescription) implements PaymentProcessorResponse{}
}
