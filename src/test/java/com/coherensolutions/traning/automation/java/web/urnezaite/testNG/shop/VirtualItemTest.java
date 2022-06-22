package com.coherensolutions.traning.automation.java.web.urnezaite.testNG.shop;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.VirtualItem;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VirtualItemTest {

    @Test(dataProvider = "itemDataProvider", groups = {"item", "shop"})
    void checkVirtualItem(String name, double price, double sizeOnDisk) {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(name);
        virtualItem.setPrice(price);
        virtualItem.setSizeOnDisk(sizeOnDisk);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(virtualItem.getName(), name);
        softAssert.assertEquals(virtualItem.getPrice(), price);
        softAssert.assertEquals(virtualItem.getSizeOnDisk(), sizeOnDisk);
        softAssert.assertAll();
    }

    @DataProvider
    Object[][] itemDataProvider() {
        Object[][] data = new Object[][]{
                {"movie", 5, 1000},
                {"lecture", 12, 30},
                {"app", 3, 50}
        };
        return data;
    }
}