package com.txy.jpetstore.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txy.jpetstore.demo.domain.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemMapper extends BaseMapper<CartItem> {
    int updateCount();
}
