package com.program.razorpay.mapper;

import com.program.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.program.razorpay.merchant.dto.response.ApiKeyResponse;
import com.program.razorpay.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    ApiKeyCreateResponse toCreateResponse(ApiKey apiKey);

    List<ApiKeyResponse> toResponseList(List<ApiKey> list);
}
