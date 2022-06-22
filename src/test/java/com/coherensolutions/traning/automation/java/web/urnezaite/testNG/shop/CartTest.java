package com.coherensolutions.traning.automation.java.web.urnezaite.testNG.shop;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.Cart;
import com.coherensolutions.traning.automation.java.web.urnezaite.shop.RealItem;
import com.coherensolutions.traning.automation.java.web.urnezaite.shop.VirtualItem;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CartTest {
    public Cart cart;
    public RealItem realItem;
    public VirtualItem virtualItem;

    @Parameters({"cartName", "realItemName", "realItemPrice", "realItemWeight", "virtualItemName", "virtualItemPrice", "virtualItemSizeOnDisk"})
    @BeforeMethod(groups = {"cart", "shop"})
    void createCart(@Optional("test") String cartName, @Optional("cat food") String realItemName, @Optional("10") double realItemPrice, @Optional("2") double realItemWeight, @Optional("app") String virtualItemName, @Optional("5") double virtualItemPrice, @Optional("50") double virtualItemSizeOnDisk) {
        cart = new Cart(cartName);

        realItem = new RealItem();
        realItem.setName(realItemName);
        realItem.setPrice(realItemPrice);
        realItem.setWeight(realItemWeight);

        virtualItem = new VirtualItem();
        virtualItem.setName(virtualItemName);
        virtualItem.setPrice(virtualItemPrice);
        virtualItem.setSizeOnDisk(virtualItemSizeOnDisk);

    }

    @Test(groups = {"cart", "shop"})
    void cartIsCreatedTest() {
        assertNotNull(cart, "cart can't be null");
    }

    @Parameters("tax")
    @Test(groups = {"cart", "shop"}, dependsOnMethods = "cartIsCreatedTest")
    void testTotalPrice(@Optional("0.2") double tax) {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        double totalExpectedPrice = realItem.getPrice() + realItem.getPrice() * tax + virtualItem.getPrice() + virtualItem.getPrice() * tax;
        assertEquals(cart.getTotalPrice(), totalExpectedPrice, "price should match");
    }

    @Test(groups = {"cart", "shop"}, dependsOnMethods = "cartIsCreatedTest")
    void testTotalPriceWhenAddingRemovingItem() {
        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);
        cart.deleteVirtualItem(virtualItem);
        assertEquals(cart.getTotalPrice(), 0, "totalPrice must be 0");
        //total price is not changed after item is deleted!!
    }

    @Parameters("cartName")
    @Test(groups = {"cart", "shop"}, dependsOnMethods = "cartIsCreatedTest")
    void testCartName(@Optional("test") String cartName) {
        assertEquals(cart.getCartName(), cartName, "cart name should be \"" + cartName + "\" ");
    }
}