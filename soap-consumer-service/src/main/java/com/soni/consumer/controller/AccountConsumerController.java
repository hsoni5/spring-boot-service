package com.soni.consumer.controller;

import com.soni.consumer.generated.AccountDetailsResponse;
import com.soni.consumer.generated.AccountRequest;
import com.soni.consumer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountConsumerController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDetailsResponse> findAccountDetails(@RequestBody AccountRequest request) {
        return new ResponseEntity<>(accountService.getAccountDetails(request), HttpStatus.OK);
    }
}
