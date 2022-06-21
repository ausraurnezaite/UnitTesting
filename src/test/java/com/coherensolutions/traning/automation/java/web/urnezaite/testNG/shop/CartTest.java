package com.coherensolutions.traning.automation.java.web.urnezaite.testNG.shop;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.Cart;
import com.coherensolutions.traning.automation.java.web.urnezaite.shop.RealItem;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CartTest {
    public Cart cart;
    public RealItem item;

    @Parameters({"cartName", "realItemName", "realItemPrice", "realItemWeight"})
    @BeforeMethod(groups = {"cart", "shop"})
    void createCart(@Optional("test") String cartName, @Optional("cat food") String realItemName, @Optional("10") double realItemPrice, @Optional("2") double realItemWeight) {
        cart = new Cart(cartName);
        System.out.println(cart);

        item = new RealItem();
        item.setName(realItemName);
        item.setPrice(realItemPrice);
        item.setWeight(realItemWeight);
        System.out.println(item);
    }

    @Test(groups = {"cart", "shop"})
    void cartIsCreatedTest() {
        assertNotNull(cart);
    }

    @Parameters("tax")
    @Test(groups = {"cart", "shop"}, dependsOnMethods = "cartIsCreatedTest")
    void testTotalPrice(@Optional("0.2") double tax) {
        cart.addRealItem(item);
        cart.addRealItem(item);
        double totalExpectedPrice = item.getPrice() + item.getPrice() * tax + item.getPrice() + item.getPrice() * tax;
        assertEquals(cart.getTotalPrice(), totalExpectedPrice);
    }

    @Test(groups = {"cart", "shop"}, dependsOnMethods = "cartIsCreatedTest")
    void testTotalPriceWhenAddingRemovingItem() {
        cart.addRealItem(item);
        cart.deleteRealItem(item);
        assertEquals(cart.getTotalPrice(), 0, "totalPrice must be 0");
        //total price is not changed after item is deleted!!
    }

    @Test(groups = {"cart", "shop"}, dependsOnMethods = "cartIsCreatedTest")
    void testCartName() {
        assertEquals(cart.getCartName(), "test");
    }
}