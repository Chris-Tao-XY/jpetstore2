package com.txy.jpetstore.demo.service;

import com.txy.jpetstore.demo.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> productList(String categoryId);
}
