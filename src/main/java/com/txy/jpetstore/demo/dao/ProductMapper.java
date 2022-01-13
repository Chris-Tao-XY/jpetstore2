package com.txy.jpetstore.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txy.jpetstore.demo.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
