package com.soni.api;

import com.soni.service.AccountService;
import com.soni.ws.api.account.AccountDetailsResponse;
import com.soni.ws.api.account.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AccountEndpoint {
    private static final String NAMESPACE_URI = "http://www.soni.com/ws/api/account";
    @Autowired
    private AccountService accountService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AccountRequest")
    @ResponsePayload
    public AccountDetailsResponse getAccountDetails(@RequestPayload AccountRequest request) {
        AccountDetailsResponse response = new AccountDetailsResponse();
        response.setAccountDetails(accountService.getFindAccountDetails(request));
        return response;
    }
}
