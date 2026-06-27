package com.program.razorpay.payment.config;

import com.program.razorpay.common.enums.PaymentMethod;
import com.program.razorpay.payment.gateway.PaymentAdapter;
import com.program.razorpay.payment.gateway.adapter.CardPaymentAdapter;
import com.program.razorpay.payment.gateway.adapter.NetBankingAdapter;
import com.program.razorpay.payment.gateway.adapter.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {
//this is for payment gateway router  class
    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.NETBANKING, new NetBankingAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter()
        );
    }
}
