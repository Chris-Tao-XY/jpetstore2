package com.txy.jpetstore.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txy.jpetstore.demo.dao.ItemMapper;
import com.txy.jpetstore.demo.dao.OrderMapper;
import com.txy.jpetstore.demo.dao.OrderToItemMapper;
import com.txy.jpetstore.demo.domain.CartItem;
import com.txy.jpetstore.demo.domain.Item;
import com.txy.jpetstore.demo.domain.Order;
import com.txy.jpetstore.demo.domain.OrderToItem;
import com.txy.jpetstore.demo.service.CartService;
import com.txy.jpetstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderToItemMapper orderToItemMapper;
    @Autowired
    private ItemMapper itemMapper   ;

    @Override
    public Order getJust(String username) {
        return orderMapper.selectOne(new QueryWrapper<Order>().eq("userid", username).orderByDesc("orderid").last(
                "limit 1"));
    }

    @Override
    @Transactional
    public int createOrder(Order order) {
        List<CartItem> cartItems = cartService.viewCart(order.getUsername());
        BigDecimal totalCount = new BigDecimal(0);
        for (CartItem cartItem : cartItems) {
            totalCount = totalCount.add(cartItem.getTotalcost());
        }
        order.setTotalPrice(totalCount);
        int insert = orderMapper.createOrder(order);
        Order order1 =
                orderMapper.selectOne(new QueryWrapper<Order>().eq("userid",
                        order.getUsername()).orderByDesc("orderid").last(
                        "limit 1"));
        OrderToItem orderToItem = new OrderToItem();
        for (CartItem cartItem : cartItems) {
            orderToItem.setItemId(cartItem.getItemid());
            orderToItem.setOrderId(order1.getOrderId());
            orderToItem.setQuantity(cartItem.getInstock());
            orderToItem.setListPrice(cartItem.getListprice());
            orderToItem.setTotalPrice(cartItem.getTotalcost());
            orderToItemMapper.insert(orderToItem);
        }
        cartService.clearCart(order.getUsername());
        return insert;
    }

    @Override
    public List<Order> getOrder(String username) {
        return orderMapper.selectList(new QueryWrapper<Order>().eq("userid", username));
    }

    @Override
    public List<CartItem> viewOrder(String orderId) {
        List<CartItem> items = new ArrayList<>();
        List<OrderToItem> orderViewItems = orderToItemMapper.selectList(new QueryWrapper<OrderToItem>().eq("orderid",
                orderId));
        for (OrderToItem orderToItem : orderViewItems) {
            items.add(new CartItem(orderToItem.getItemId(),orderToItem.getQuantity(),orderToItem.getTotalPrice(),orderToItem.getListPrice()));
        }
        return items;
    }

}
