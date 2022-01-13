package com.txy.jpetstore.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@TableName("ordertoitem")
public class OrderToItem {
    public OrderToItem() {
    }

    public OrderToItem(String orderId, String itemId, Integer quantity, BigDecimal listPrice, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.totalPrice = totalPrice;
    }

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("orderid")
    private String orderId;
    @TableField("itemId")
    private String itemId;
    @TableField("quantity")
    private Integer quantity;
    @TableField("listprice")
    private BigDecimal listPrice;
    @TableField("totalprice")
    private BigDecimal totalPrice;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
