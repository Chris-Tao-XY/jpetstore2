package com.txy.jpetstore.demo.service.impl;

import com.txy.jpetstore.demo.dao.AccountMapper;
import com.txy.jpetstore.demo.dao.SignonMapper;
import com.txy.jpetstore.demo.domain.Account;
import com.txy.jpetstore.demo.domain.Signon;

import com.txy.jpetstore.demo.exception.AlreadyRegisteredException;
import com.txy.jpetstore.demo.exception.NotRegisteredException;
import com.txy.jpetstore.demo.exception.UpdateWrongException;
import com.txy.jpetstore.demo.exception.WrongPasswordException;
import com.txy.jpetstore.demo.service.SignonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

@Service
public class SignonServiceImpl implements SignonService {

    @Autowired
    private SignonMapper signonMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public int register(Signon signon) throws AlreadyRegisteredException{
        if (Objects.equals(null, signonMapper.selectById(signon.getUsername()))) {
            accountMapper.insertOneWhenRegister(signon.getUsername());
            return signonMapper.insert(signon);
        } else {
            throw new AlreadyRegisteredException(signon.getUsername()+ " has already registed!");
        }
    }

    @Override
    public Account login(Signon signon) {
        Signon getSignon = signonMapper.selectById(signon.getUsername());
        if (getSignon==null) {
            throw new NotRegisteredException(signon.getUsername()+" not exists");
        } else if (!getSignon.getPassword().equals(signon.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }else{
            stringRedisTemplate.opsForValue().set("loginAccount", signon.getUsername(), Duration.ofHours(2));
            return accountMapper.selectOne(signon.getUsername());
        }
    }

    @Override
    public Signon hasOne(String username) {
        Signon getSignon = signonMapper.selectById(username);
        if (getSignon==null) {
            throw new NotRegisteredException(username+" not exists");
        }else{
            return getSignon;
        }
    }

    @Transactional
    @Override
    public Account updateImf(Account account){
        int res= accountMapper.updateById(account);
        if (res==1){
            return accountMapper.selectOne(account.getUsername());
        }else{
            throw new UpdateWrongException("Wrong update");
        }
    }

    @Override
    public Account getImf(String username) {
        Account getAccount = accountMapper.selectOne(username);
        if (getAccount==null) {
            throw new NotRegisteredException(username + " not exists");
        }
        return getAccount;
    }

    @Override
    public int updatePassword(Signon signon) {
        return signonMapper.updateById(signon);
    }


}
