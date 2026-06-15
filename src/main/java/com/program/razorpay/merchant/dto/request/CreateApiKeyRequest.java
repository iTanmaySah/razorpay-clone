package com.program.razorpay.merchant.dto.request;

import com.codingshuttle.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
