package com.soni.consumer.service;

import com.soni.consumer.generated.AccountDetailsResponse;
import com.soni.consumer.generated.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class AccountService {
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Value("${soap.service.endpoint}")
    private String endPoint;

    public AccountDetailsResponse getAccountDetails(AccountRequest request) {
        return (AccountDetailsResponse) webServiceTemplate.marshalSendAndReceive(endPoint + "/account-details", request);
    }
}
