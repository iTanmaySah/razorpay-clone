package com.program.razorpay.payment.mapper;

import com.program.razorpay.payment.dto.response.OrderResponse;
import com.program.razorpay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper  {
     OrderResponse toResponse(OrderRecord orderRecord);
}
