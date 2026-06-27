package com.program.razorpay.payment.config;

import com.program.razorpay.common.enums.PaymentMethod;
import com.program.razorpay.payment.processor.PaymentProcessor;
import com.program.razorpay.payment.processor.strategy.CardPaymentProcessor;
import com.program.razorpay.payment.processor.strategy.NetBankingPaymentProcessor;
import com.program.razorpay.payment.processor.strategy.UpiPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentProcessorConfig {

    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentProcessorMap(){
        return Map.of(PaymentMethod.CARD, new CardPaymentProcessor(),
                PaymentMethod.NETBANKING, new NetBankingPaymentProcessor(),
                PaymentMethod.UPI, new UpiPaymentProcessor()
        );
    }
}
