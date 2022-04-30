package com.lhc.pojo;

import java.util.List;

public class CartItem {
    //这个id就是商品Goods的id
    private Integer id;
    private String name;
    private Integer count;
    private Integer price;
    private Integer totalPrice;
    private Integer stock = 0;
    private Integer sellerId;

    public CartItem(Integer id, String name, Integer count, Integer price, Integer totalPrice, Integer stock, Integer sellerId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.stock = stock;
        this.sellerId = sellerId;
    }

//    public List<String> getNameList(List<CartItem> cartItem){
//        List<String> nameList = null;
//        for (CartItem item : cartItem) {
//            String name = item.getName();
//            nameList.add(name);
//        }
//        return nameList;
//    }

    public CartItem() {
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", stock=" + stock +
                ", sellerId=" + sellerId +
                '}';
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
}
