package com.coherensolutions.traning.automation.java.web.urnezaite.junit.shop;

import com.coherensolutions.traning.automation.java.web.urnezaite.shop.RealItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RealItemTest {
    @ParameterizedTest
    @MethodSource("itemArgumentsProvider")
    void checkRealItem(String name, double price, double weight) {
        RealItem realItem = new RealItem();
        realItem.setName(name);
        realItem.setPrice(price);
        realItem.setWeight(weight);

        Assertions.assertAll("properties should match",
                () -> Assertions.assertEquals(name, realItem.getName()),
                () -> Assertions.assertEquals(price, realItem.getPrice()),
                () -> Assertions.assertEquals(weight, realItem.getWeight())
        );
    }

    static Stream<Arguments> itemArgumentsProvider() {
        return Stream.of(
                arguments("cat", 1000, 5.2),
                arguments("phone", 600, 0.5),
                arguments("shampoo", 15, 1)
        );
    }
}
