package com.txy.jpetstore.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@TableName("cartitem")
@Data
@ToString
public class CartItem {
    @TableField("userid")
    private String username;
    @TableField("itemid")
    private String itemid;
    @TableField("instock")
    private Integer instock;
    @TableField("totalcost")
    private BigDecimal totalcost;
    @TableField("listprice")
    private BigDecimal listprice;

    public CartItem() {
    }

    public CartItem(String itemid, Integer instock, BigDecimal totalcost, BigDecimal listprice) {
        this.itemid = itemid;
        this.instock = instock;
        this.totalcost = totalcost;
        this.listprice = listprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Integer getInstock() {
        return instock;
    }

    public void setInstock(Integer instock) {
        this.instock = instock;
    }

    public BigDecimal getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(BigDecimal totalcost) {
        this.totalcost = totalcost;
    }

    public BigDecimal getListprice() {
        return listprice;
    }

    public void setListprice(BigDecimal listprice) {
        this.listprice = listprice;
    }
}
