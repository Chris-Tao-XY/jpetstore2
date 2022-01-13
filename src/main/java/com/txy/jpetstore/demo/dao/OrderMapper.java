package com.txy.jpetstore.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txy.jpetstore.demo.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    int createOrder(Order order) ;
//    Order selectJustOne(String username);
}
