package com.txy.jpetstore.demo.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("product")
public class Product{
  @TableId("productid")
  private String productId;
  @TableField("category")
  private String categoryId;
  private String name;
  @TableField("descn")
  private String description;

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId.trim();
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Product{" +
            "productId='" + productId + '\'' +
            ", categoryId='" + categoryId + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
