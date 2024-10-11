package com.systex.demo.service;

import com.systex.demo.model.Account;
import com.systex.demo.model.AccountRepository;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class RegisterService {
    @Resource
    AccountRepository accountRepository;
    public boolean hasAccountName(String account){
        if(accountRepository.findByAccNum(account)!=null){
            return true;
        }
        else{
            return false;
        }
    }
    public void successRegis(Account account){
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        accountRepository.save(account);
    }
}
