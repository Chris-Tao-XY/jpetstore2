package com.txy.jpetstore.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txy.jpetstore.demo.dao.CartItemMapper;
import com.txy.jpetstore.demo.domain.CartItem;
import com.txy.jpetstore.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public int addItem(CartItem cartItem) {
        return cartItemMapper.insert(cartItem);
    }

    @Override
    public int updateItem(CartItem cartItem) {
        return cartItemMapper.update(cartItem, new QueryWrapper<CartItem>().eq("userid", cartItem.getUsername()).eq(
                "itemid",cartItem.getItemid()));
    }

    @Override
    public int deleteItem(CartItem cartItem) {
        return cartItemMapper.delete(new QueryWrapper<CartItem>().eq("itemid", cartItem.getItemid()).eq("userid", cartItem.getUsername()));
    }

    @Override
    public List<CartItem> viewCart(String username) {
        return cartItemMapper.selectList(new QueryWrapper<CartItem>().eq("userid", username));
    }

    @Override
    public int clearCart(String username) {
        return cartItemMapper.delete(new QueryWrapper<CartItem>().eq("userid", username));
    }
}
