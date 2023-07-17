package com.soni.validators.service;

import com.soni.validators.dto.OnCreate;
import com.soni.validators.dto.OnUpdate;
import com.soni.validators.entity.InputWithCustomValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
class ValidatingServiceWithGroups {

    @Validated(OnCreate.class)
    void validateForCreate(@Valid InputWithCustomValidator input) {
        // do something
    }

    @Validated(OnUpdate.class)
    void validateForUpdate(@Valid InputWithCustomValidator input) {
        // do something
    }

}