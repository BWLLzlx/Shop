package com.lhc.test;

import com.lhc.pojo.Cart;
import com.lhc.pojo.CartItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(2,"222",1,20,20,0,1));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(2,"222",1,20,20,0,1));

        cart.deleteItem(2);

        System.out.println(cart);
    }

    @Test
    public void updateItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(2,"222",1,20,20,0,1));

        System.out.println(cart);

        cart.updateItem(1,30);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(1,"111",1,1,1,0,1));
        cart.addItem(new CartItem(2,"222",1,20,20,0,1));

        cart.clear();

        System.out.println(cart);
    }
}