package com.txy.jpetstore.demo.service;

import com.txy.jpetstore.demo.domain.CartItem;
import com.txy.jpetstore.demo.domain.Item;
import com.txy.jpetstore.demo.domain.Order;

import java.util.List;

public interface OrderService {
    int createOrder(Order order);
    List<Order> getOrder(String username);
    Order getJust(String username);
    List<CartItem> viewOrder(String orderId);

}
