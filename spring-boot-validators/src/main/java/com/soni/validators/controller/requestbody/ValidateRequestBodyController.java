package com.soni.validators.controller.requestbody;

import com.soni.validators.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "customers")
@Validated
public class ValidateRequestBodyController {
    @PostMapping
    ResponseEntity<String> validateBody(@Valid @RequestBody CustomerRequest customerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            List<ObjectError> errors = bindingResult.getAllErrors();
            // Process and return the validation errors as needed
            return ResponseEntity.badRequest().body(errors.toString());
        }
        return ResponseEntity.ok("User created successfully");
    }

}
