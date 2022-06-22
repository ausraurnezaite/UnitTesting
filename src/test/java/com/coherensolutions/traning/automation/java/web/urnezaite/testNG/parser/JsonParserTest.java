package com.coherensolutions.traning.automation.java.web.urnezaite.testNG.parser;

import com.coherensolutions.traning.automation.java.web.urnezaite.parser.JsonParser;
import com.coherensolutions.traning.automation.java.web.urnezaite.parser.NoSuchFileException;
import com.coherensolutions.traning.automation.java.web.urnezaite.shop.Cart;
import com.coherensolutions.traning.automation.java.web.urnezaite.shop.RealItem;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;

import static org.testng.Assert.*;


public class JsonParserTest {
    private final JsonParser parser = new JsonParser();
    private Cart cart;

    @BeforeTest(groups = {"parser", "shop"})
    void createCart() {
        cart = new Cart("test");

        RealItem item = new RealItem();
        item.setName("Cat Food");
        item.setPrice(20);
        item.setWeight(2);
        cart.addRealItem(item);
    }

    @AfterMethod(groups = {"parser", "shop"})
    void deleteFile() {
        new File(String.format("src/main/resources/%s.json", cart.getCartName())).delete();
    }

    @Test(groups = {"parser", "shop"})
    void checkIfWrittenAndReadDataIsCorrect() {
        SoftAssert softAssert = new SoftAssert();
        parser.writeToFile(cart);
        Cart cartFromFile = parser.readFromFile(new File(String.format("src/main/resources/%s.json", cart.getCartName())));
        softAssert.assertEquals(cartFromFile.getCartName(), cart.getCartName(), "names should match");
        softAssert.assertEquals(cartFromFile.getTotalPrice(), cart.getTotalPrice(), "total price should match");
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidPathProvider", expectedExceptions = NoSuchFileException.class, groups = {"parser", "shop"})
    void checkIfThrowsNoSuchFileException(String path) {
        parser.readFromFile(new File(path));
    }

    @DataProvider
    public Object[][] invalidPathProvider() {
        String[][] data = new String[][]{
                {"src/main/resources/cart.json"},
                {"src/andrew-cart.json"},
                {"src/eugen-cart.json"},
                {"src/main/test.json"},
                {"src/main/resources/test2.json"}
        };
        return data;
    }

    @Test(dataProvider = "pathProvider", groups = {"parser", "shop"})
    void checkThatEOFExceptionIsNotThrown(String path) {
        parser.readFromFile(new File(path));
    }

    @DataProvider
    public Object[][] pathProvider() {
        String[][] data = new String[][]{
                {"src/main/resources/andrew-cart.json"},
                {"src/main/resources/eugen-cart.json"}
        };
        return data;
    }

    @Test(groups = {"parser", "shop"})
    void testFileIsCreated() {
        String fileName = String.format("src/main/resources/%s.json", cart.getCartName());
        parser.writeToFile(cart);
        File file = new File(fileName);
        assertTrue(file.exists(), String.format("file: %s must exist after being written", fileName));
    }
}