package com.txy.jpetstore.demo.service;

import com.txy.jpetstore.demo.domain.CartItem;

import java.util.List;

public interface CartService {
    int addItem(CartItem cartItem);
    int updateItem(CartItem cartItem);
    int deleteItem(CartItem cartItem);
    List<CartItem> viewCart(String username);
    int clearCart(String username);

}
