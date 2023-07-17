package com.soni.validators.service;

import javax.validation.Valid;

import com.soni.validators.dto.CustomerRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
class ValidatingService {

    void validateInput(@Valid CustomerRequest input){
        // do something
    }

}