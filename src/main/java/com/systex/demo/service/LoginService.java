package com.systex.demo.service;

import com.systex.demo.model.Account;
import com.systex.demo.model.AccountRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Resource
    AccountRepository accountRepository;
    public Account checkAccountName(String accountNum){
        Account account = accountRepository.findByAccNum(accountNum);
        return account;
    }

}
