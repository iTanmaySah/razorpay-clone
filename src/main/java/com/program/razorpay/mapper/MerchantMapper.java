package com.program.razorpay.mapper;

import com.program.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.program.razorpay.merchant.dto.response.MerchantResponse;
import com.program.razorpay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface MerchantMapper {
    Merchant toEntityFromSignUpRequest(MerchantSignupRequest request);

    MerchantResponse toResponse(Merchant merchant);
}
