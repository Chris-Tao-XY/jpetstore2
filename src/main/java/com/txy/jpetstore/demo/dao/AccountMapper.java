package com.txy.jpetstore.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txy.jpetstore.demo.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper extends BaseMapper<Account> {
    int insertOneWhenRegister(String username);
    Account selectOne(String username);
    int updateImf(Account account);
}
