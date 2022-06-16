package com.coherensolutions.traning.automation.java.web.urnezaite.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {
    public Cart cart;
    public RealItem item;

    @BeforeEach
    public void createCart() {
        cart = new Cart("test");

        item = new RealItem();
        item.setName("catfood");
        item.setPrice(10);
        item.setWeight(2);
    }

    @Test
    void testTotalPrice() {
        double tax = 0.2;
        cart.addRealItem(item);
        cart.addRealItem(item);
        double totalExpectedPrice = item.getPrice() + item.getPrice() * tax + item.getPrice() + item.getPrice() * tax;
        Assertions.assertEquals(totalExpectedPrice, cart.getTotalPrice());
    }

    @Test
    void testTotalPriceWhenAddingRemovingItem() {
        cart.addRealItem(item);
        cart.deleteRealItem(item);
        Assertions.assertEquals(0, cart.getTotalPrice(), "totalPrice must be 0");
        //total price is not changed after item is deleted!!
    }

    @Test
    void testCartName() {
        Assertions.assertEquals("test", cart.getCartName());
    }

}
