package com.txy.jpetstore.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txy.jpetstore.demo.dao.ItemMapper;
import com.txy.jpetstore.demo.domain.Item;
import com.txy.jpetstore.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> search(String name) {
        List<Item> itemList = itemMapper.selectList(new QueryWrapper<Item>().like("itemid", name));
        return itemList;
    }

    @Override
    public List<Item> searchByProduct(String name) {
        return itemMapper.selectList(new QueryWrapper<Item>().eq("productid",name));
    }

    @Override
    public Item searchOne(String name) {
        return itemMapper.selectById(name);
    }
}
