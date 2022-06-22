package com.coherensolutions.traning.automation.java.web.urnezaite.testNG.shop;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.RealItem;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class RealItemTest {

    @Test(dataProvider = "itemDataProvider", groups = {"item", "shop"})
    void checkRealItem(String name, double price, double weight) {
        RealItem realItem = new RealItem();
        realItem.setName(name);
        realItem.setPrice(price);
        realItem.setWeight(weight);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(realItem.getName(), name);
        softAssert.assertEquals(realItem.getPrice(), price);
        softAssert.assertEquals(realItem.getWeight(), weight);
        softAssert.assertAll();
    }

    @DataProvider
    Object[][] itemDataProvider() {
        Object[][] data = new Object[][]{
                {"cat", 1000, 5.2},
                {"phone", 600, 0.5},
                {"shampoo", 15, 1}
        };
        return data;
    }

    @Test(dataProvider = "itemDataProvider", groups = {"item", "shop"})
    void toStringTest(String name, double price, double weight) {
        RealItem realItem = new RealItem();
        realItem.setName(name);
        realItem.setPrice(price);
        realItem.setWeight(weight);
        String expected = "Class: " + realItem.getClass() + "; Name: " + realItem.getName() + "; Price: " + realItem.getPrice() + "; Weight: " + realItem.getWeight();
        assertEquals(expected, realItem.toString());
    }

}