package com.program.razorpay.merchant.dto.request;

import com.program.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
