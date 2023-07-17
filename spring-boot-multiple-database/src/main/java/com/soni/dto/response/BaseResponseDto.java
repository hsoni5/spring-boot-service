package com.soni.dto.response;

import com.soni.dto.BaseDto;
import com.soni.dto.Error;
import lombok.Data;

@Data
public class BaseResponseDto extends BaseDto {
    private Error error;
}