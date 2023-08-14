package com.soni.service;

import com.soni.model.Account;
import com.soni.repository.AccountRepository;
import com.soni.ws.api.account.AccountDetails;
import com.soni.ws.api.account.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public AccountDetails getFindAccountDetails(AccountRequest accountRequest) {
        AccountDetails accountDetails = new AccountDetails();
        Account account = accountRepository.findByAccountNumber(accountRequest.getAccountNumber()).orElseGet(Account::new);
        accountDetails.setAccountNumber(account.getAccountNumber());
        accountDetails.setName(account.getName());
        accountDetails.setType(account.getType());
        accountDetails.setBalance(account.getBalance());
        return accountDetails;
    }
}
