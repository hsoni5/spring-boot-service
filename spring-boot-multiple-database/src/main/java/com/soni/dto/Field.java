package com.soni.dto;


import lombok.Data;

@Data
public class Field extends BaseDto {
    private String name;
    private String[] messages;
}
