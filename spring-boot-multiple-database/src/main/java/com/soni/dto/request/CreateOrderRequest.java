package com.soni.dto.request;

import com.soni.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderRequest extends BaseDto {
    @NotEmpty
    private String merchantId;
    @NotEmpty
    private String merchantOrderId;
    @NotEmpty
    private String userId;
    @NotNull
    private Double amount;

}