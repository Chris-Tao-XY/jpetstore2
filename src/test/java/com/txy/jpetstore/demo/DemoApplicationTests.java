package com.txy.jpetstore.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txy.jpetstore.demo.dao.AccountMapper;
import com.txy.jpetstore.demo.dao.ProductMapper;
import com.txy.jpetstore.demo.domain.Account;
import com.txy.jpetstore.demo.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    ProductMapper productMapper;
    @Test
    public void testString() {
        System.out.println(productMapper.selectList(new QueryWrapper<Product>().eq("category","FISH")));
    }



}
