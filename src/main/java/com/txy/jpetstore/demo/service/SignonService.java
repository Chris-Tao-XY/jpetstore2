package com.txy.jpetstore.demo.service;

import com.txy.jpetstore.demo.domain.Account;
import com.txy.jpetstore.demo.domain.Signon;
import org.springframework.stereotype.Service;


public interface SignonService{
    int register(Signon signon);
    Account login(Signon signon);
    Account updateImf(Account account);
    Account getImf(String username);
    int updatePassword(Signon signon);
    Signon hasOne(String username);
}
