package com.soni.validators.controller.parameter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value = "valid")
public class ValidateParametersController {
    @GetMapping("/{id}")
    ResponseEntity<String> validatePathVariable(
            @PathVariable("id") @Min(5) int id) {
        return ResponseEntity.ok("Customer get successfully");
    }
    @GetMapping()
    ResponseEntity<String> validateRequestParameter(
            @RequestParam("param") @Min(5) int param) {
        return ResponseEntity.ok("Customer get successfully");
    }

}
