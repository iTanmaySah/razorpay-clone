package com.program.razorpay.merchant.service;

import com.program.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.program.razorpay.merchant.dto.response.MerchantResponse;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest request);
}
