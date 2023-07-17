package com.soni.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderResponse extends BaseResponseDto {
    private Long id;
    private String trackingId;
    private String merchantId;
    private String merchantOrderId;
    private String userId;
    private Double amount;
}
