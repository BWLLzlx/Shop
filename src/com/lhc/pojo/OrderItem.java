package com.lhc.pojo;

import java.util.Date;

public class OrderItem {
    private Integer id;
    private String name;
    private Integer count;
    private Integer price;
    private Integer totalPrice;
    private String orderId;
    private Integer sellerId;
    private Date CreateTime;
    private Integer goodsId;
    private Integer userId;

    public OrderItem(Integer id, String name, Integer count, Integer price, Integer totalPrice, String orderId, Integer sellerId, Date createTime, Integer goodsId, Integer userId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.sellerId = sellerId;
        CreateTime = createTime;
        this.goodsId = goodsId;
        this.userId = userId;
    }

    public OrderItem() {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                ", sellerId=" + sellerId +
                ", CreateTime=" + CreateTime +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                '}';
    }
}
