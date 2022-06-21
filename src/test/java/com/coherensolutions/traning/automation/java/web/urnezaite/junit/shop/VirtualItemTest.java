package com.coherensolutions.traning.automation.java.web.urnezaite.junit.shop;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.VirtualItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class VirtualItemTest {

    @ParameterizedTest
    @CsvSource({
            "movie,    5,   1000",
            "music,    1,   9",
            "lecture,  12, 30",
            "app,  3, 50"
    })
    void checkVirtualItem(String name, double price, double sizeOnDisk) {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(name);
        virtualItem.setPrice(price);
        virtualItem.setSizeOnDisk(sizeOnDisk);

        Assertions.assertAll("properties should match",
                () -> Assertions.assertEquals(name, virtualItem.getName()),
                () -> Assertions.assertEquals(price, virtualItem.getPrice()),
                () -> Assertions.assertEquals(sizeOnDisk, virtualItem.getSizeOnDisk())
        );
    }
}
