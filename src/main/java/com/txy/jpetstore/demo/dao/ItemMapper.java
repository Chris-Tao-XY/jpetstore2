package com.txy.jpetstore.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txy.jpetstore.demo.domain.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper extends BaseMapper<Item> {

}
