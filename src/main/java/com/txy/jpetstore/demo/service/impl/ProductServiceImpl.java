package com.txy.jpetstore.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txy.jpetstore.demo.dao.ProductMapper;
import com.txy.jpetstore.demo.domain.Product;
import com.txy.jpetstore.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> productList(String categoryId){
        return productMapper.selectList(new QueryWrapper<Product>().eq("category",categoryId));
    }
}
