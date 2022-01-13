package com.txy.jpetstore.demo.service;

import com.txy.jpetstore.demo.domain.Item;

import java.util.List;

public interface ItemService {
    List<Item> search(String name);
    Item searchOne(String name);
    List<Item> searchByProduct(String name);
}
