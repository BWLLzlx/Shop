package com.lhc.pojo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
//    private Integer totalCount;
//    private Integer totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public void addItem(CartItem cartItem){
        //先看购物车有没有这个商品
        //如果有酒添加数量改变总金额
        //如果没有酒放到集合中

        //根据传递过来的id去map里面找，找到了就不是null
        CartItem item = items.get(cartItem.getId());

        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice()*item.getCount());
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);
    }

    public void updateItem(Integer id,Integer count){
        //先查看购物车是否有此商品，如果有就改
        CartItem item = items.get(id);
        if (item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice()*item.getCount());
        }
    }

    public void clear(){
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()) {
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
