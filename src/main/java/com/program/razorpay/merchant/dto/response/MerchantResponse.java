package com.program.razorpay.merchant.dto.response;

import com.program.razorpay.common.enums.BusinessType;
import com.program.razorpay.common.enums.MerchantStatus;
import lombok.Data;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}
