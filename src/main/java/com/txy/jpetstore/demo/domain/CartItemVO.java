package com.txy.jpetstore.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
public class CartItemVO {
    private String username;
    private String itemid;
    private Integer quantity;
    private BigDecimal totalcost;
    private BigDecimal listprice;
    private String productId;
    private String description;

    public CartItemVO() {
    }

    public CartItemVO(Item item,CartItem cartItem){
        this.username = cartItem.getUsername();
        this.itemid = cartItem.getItemid();
        this.quantity = cartItem.getInstock();
        this.listprice = cartItem.getListprice();
        this.totalcost=cartItem.getTotalcost();
        this.productId=item.getProductId();
        this.description=item.getAttribute1();
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }
}
