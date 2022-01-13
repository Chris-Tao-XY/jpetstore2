package com.txy.jpetstore.demo.controller;

import com.txy.jpetstore.demo.domain.CartItem;
import com.txy.jpetstore.demo.domain.Item;
import com.txy.jpetstore.demo.domain.Order;
import com.txy.jpetstore.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{username}")
    public Map<String, Object> viewOrders(@PathVariable("username") String username) {
        List<Order> orderList = orderService.getOrder(username);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "ok");
        map.put("order", orderList);
        return map;
    }

    @GetMapping("/one/{username}")
    public Map<String, Object> viewJust(@PathVariable("username") String username) {
        Order order = orderService.getJust(username);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "ok");
        map.put("order", order);
        return map;
    }


    @PostMapping("/{username}")
    public Map<String, Object> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "ok");
        return map;
    }

    @GetMapping("/view/{orderId}")
    public Map<String, Object> viewOrder(@PathVariable("orderId") String orderId){
        Map<String, Object> map = new HashMap<>();
        List<CartItem> cartItems = orderService.viewOrder(orderId);
        map.put("code", 200);
        map.put("message", "ok");
        map.put("items", cartItems);
        return map;
    }

}
