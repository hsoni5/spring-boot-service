package com.soni.validators.service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import com.soni.validators.dto.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public class ProgrammaticallyValidatingService {

    private Validator validator;

    public ProgrammaticallyValidatingService(Validator validator) {
        this.validator = validator;
    }

    public void validateInput(CustomerRequest input) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public void validateInputWithInjectedValidator(CustomerRequest input) {
        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}