package com.lhc.pojo;

import java.math.BigDecimal;

public class Goods {
    private Integer id;
    private String name;
    private Integer price;
    private Integer sales;
    private Integer stock;
    private String imgPath="static/img/1.png";
    private Integer sellerId = 1;
    private Integer categoryId;

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                ", sellerId=" + sellerId +
                ", categoryId=" + categoryId +
                '}';
    }

    public Goods(Integer id, String name, Integer price, Integer sales, Integer stock, String imgPath, Integer sellerId, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        //封面不能为空
        if(imgPath != null && !"".equals(imgPath)){
            this.imgPath = imgPath;
        }
        this.sellerId = sellerId;
        this.categoryId = categoryId;
    }

    public Goods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        //封面不能为空
        if(imgPath != null && !"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
