package org.soni.test.controller;

import org.soni.test.advice.SecretResponse;
import org.soni.test.dto.TestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tests")
public class TestController {

    @PostMapping
    @SecretResponse(encode = true)
    @ResponseBody
    public ResponseEntity<TestDto> test(@RequestBody TestDto testDto){
        return  new ResponseEntity<>(testDto, HttpStatus.OK);
    }

/*
    The methods of RequestBodyAdvice run first. Then the controller methods run after Spring MVC calls all other RequestBodyAdvices.
*/
}
