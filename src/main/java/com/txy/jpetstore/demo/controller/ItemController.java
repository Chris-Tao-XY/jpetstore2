package com.txy.jpetstore.demo.controller;

import com.txy.jpetstore.demo.domain.Item;
import com.txy.jpetstore.demo.service.ItemService;
import com.txy.jpetstore.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/item")
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/search/{name}")
    public Map<String, Object> search(@PathVariable("name") String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "ok");
        map.put("category", itemService.search(name));
        return map;
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/view/{category}")
    public Map<String, Object> viewOneCategory(@PathVariable("category") String category) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("message", "ok");
        map.put("category", productService.productList(category));
        return map;
    }

    @GetMapping("/searchOne/{name}")
    public Map<String, Object> searchOne(@PathVariable("name") String name) {
        Map<String, Object> map = new HashMap<>();
        Item searchOne = itemService.searchOne(name);
        if (searchOne != null) {
            map.put("code", 200);
            map.put("message", "ok");
            map.put("category", searchOne);
            stringRedisTemplate.opsForValue().set("searchOne", searchOne.getItemId());
        } else {
            map.put("code", 501);
            map.put("message", "no");
            map.put("WrongMessage", "no such item!");
        }
        return map;
    }

    @GetMapping("/searchResult")
    public Map<String, Object> searchResult() {
        Map<String, Object> map = new HashMap<>();
        String searchOne = stringRedisTemplate.opsForValue().get("searchOne");
        if (null != searchOne) {
            map.put("code", 200);
            map.put("message", "ok");
            map.put("category", itemService.searchOne(searchOne));
        }else{
            map.put("code", 501);
            map.put("message", "no");
            map.put("WrongMessage", "no such item!");
        }
        return map;
    }


    @GetMapping("/searchListResult")
    public Map<String, Object> searchListResult() {
        Map<String, Object> map = new HashMap<>();
        String searchList = stringRedisTemplate.opsForValue().get("searchList");
        if (null != searchList) {
            map.put("code", 200);
            map.put("message", "ok");
            map.put("category", itemService.searchByProduct(searchList));
        }else{
            map.put("code", 501);
            map.put("message", "no");
            map.put("WrongMessage", "no such item!");
        }
        return map;
    }

    @GetMapping("/searchList/{productId}")
    public void searchList(@PathVariable("productId") String productId) {
        stringRedisTemplate.opsForValue().set("searchList", productId);
    }
}
