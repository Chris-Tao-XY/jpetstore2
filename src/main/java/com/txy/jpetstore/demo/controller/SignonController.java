package com.txy.jpetstore.demo.controller;


import com.txy.jpetstore.demo.domain.Account;
import com.txy.jpetstore.demo.domain.Order;
import com.txy.jpetstore.demo.domain.Signon;

import com.txy.jpetstore.demo.exception.AlreadyRegisteredException;
import com.txy.jpetstore.demo.exception.NotRegisteredException;
import com.txy.jpetstore.demo.exception.UpdateWrongException;
import com.txy.jpetstore.demo.exception.WrongPasswordException;
import com.txy.jpetstore.demo.service.SignonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping
public class SignonController {
    @Resource
    private SignonService signonService;


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PutMapping("/register")
    public Map<String, Object> signin(@RequestBody Signon signon) {
        Map<String, Object> map = new HashMap<>();
        try {
            signonService.register(signon);
            map.put("code", 200);
            map.put("message", "ok");
            map.put("signinAccount", signon);
        } catch (AlreadyRegisteredException annotation) {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", signon.getUsername() + " already registered");
        } finally {
            return map;
        }
    }

    @GetMapping("/hasOne/{username}")
    public Map<String, Object> hasOne(@PathVariable("username") String username) {
        Map<String, Object> map = new HashMap<>();
        try {
            Signon signon = signonService.hasOne(username);
            map.put("code", 200);
            map.put("message", "ok");
            map.put("getSignon", signon);
        } catch (NotRegisteredException notRegisteredException) {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", username + " has not registered yet");
        } finally {
            return map;
        }
    }

    @GetMapping("/account/{username}")
    public Map<String, Object> get(@PathVariable("username") String username) {
        Map<String, Object> map = new HashMap<>();
        try {
            Account account = signonService.getImf(username);
            map.put("code", 200);
            map.put("message", "ok");
            map.put("loginAccount", account);
        } catch (NotRegisteredException notRegisteredException) {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", username + " has not registered yet");
        } finally {
            return map;
        }
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Signon signon) {
        Map<String, Object> map = new HashMap<>();
        try {
            Account account = signonService.login(signon);
            map.put("code", 200);
            map.put("message", "ok");
            map.put("loginAccount", account);
        } catch (NotRegisteredException notRegisteredException) {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", signon.getUsername() + " has not registered yet");
        } catch (WrongPasswordException wrongPasswordException) {
            map.put("code", 502);
            map.put("message", "no");
            map.put("wrongmessage", signon.getUsername() + " wrong password");
        } finally {
            return map;
        }
    }


    @PutMapping("/account/{username}")
    public Map<String, Object> update(@RequestBody Account account) {
        Map<String, Object> map = new HashMap<>();
        try {
            Account newAccount = signonService.updateImf(account);
            map.put("code", 200);
            map.put("message", "ok");
            map.put("loginAccount", newAccount);
        } catch (UpdateWrongException updateWrongException) {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", account.getUsername() + " updates failed");
        } finally {
            return map;
        }

    }

    @PutMapping("/account/password/{username}")
    public Map<String, Object> password(@RequestBody Signon signon) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "ok");
        return map;
    }

    @GetMapping("/isSigin")
    public Map<String, Object> isSigin() {
        Map<String, Object> map = new HashMap<>();
        String loginAccount = stringRedisTemplate.opsForValue().get("loginAccount");
        if (null == loginAccount) {
            map.put("code", 501);
            map.put("message", "no");
            map.put("wrongmessage", "please login first");
        } else {
            map.put("code", 200);
            map.put("message", "ok");
            map.put("loginaccout", loginAccount);
        }
        return map;
    }

    @GetMapping("/signout")
    public Map<String, Object> signout() {
        Map<String, Object> map = new HashMap<>();
        stringRedisTemplate.delete("loginAccount");
        map.put("code", 200);
        map.put("message", "ok");
        return map;
    }

    @PostMapping("/preOrder/{username}")
    public void save(@RequestBody Map<String, String> params) {
        for (String s : params.keySet()) {
            stringRedisTemplate.opsForValue().set(s,params.get(s));
        }
    }

    @GetMapping("/preOrder/{username}")
    public Map<String,Object> get() {
       Map<String,Object> map = new HashMap<>();
       map.put("code", 200);
       map.put("message", "ok");
       map.put("billtofirstname",stringRedisTemplate.opsForValue().get("billtofirstname"));
       map.put("billtolastname",stringRedisTemplate.opsForValue().get("billtolastname"));
       map.put("billaddr1",stringRedisTemplate.opsForValue().get("billaddr1"));
       map.put("billaddr2",stringRedisTemplate.opsForValue().get("billaddr2"));
       map.put("billcity",stringRedisTemplate.opsForValue().get("billcity"));
       map.put("billstate",stringRedisTemplate.opsForValue().get("billstate"));
       map.put("billzip",stringRedisTemplate.opsForValue().get("billzip"));
       map.put("billcountry",stringRedisTemplate.opsForValue().get("billcountry"));
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       map.put("orderdate",df.format(new Date()));
       map.put("cardtype",stringRedisTemplate.opsForValue().get("cardtype"));
       map.put("creditcard",stringRedisTemplate.opsForValue().get("creditcard"));
       map.put("exprdate",stringRedisTemplate.opsForValue().get("exprdate"));
       return map;
    }

}
