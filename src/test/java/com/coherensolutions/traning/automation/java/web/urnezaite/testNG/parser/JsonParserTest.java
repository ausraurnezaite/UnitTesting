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

    @BeforeTest
    void createCart() {
        cart = new Cart("test");

        RealItem item = new RealItem();
        item.setName("Cat Food");
        item.setPrice(20);
        item.setWeight(2);
        cart.addRealItem(item);
    }

    @AfterMethod
    void deleteFile() {
        new File(String.format("src/main/resources/%s.json", cart.getCartName())).delete();
    }

    @Test
    void checkIfWrittenAndReadDataIsCorrect() {
        SoftAssert softAssert = new SoftAssert();
        parser.writeToFile(cart);
        Cart cartFromFile = parser.readFromFile(new File(String.format("src/main/resources/%s.json", cart.getCartName())));
        softAssert.assertEquals(cartFromFile.getCartName(), cart.getCartName());
        softAssert.assertEquals(cartFromFile.getTotalPrice(), cart.getTotalPrice());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidPathProvider", expectedExceptions = NoSuchFileException.class)
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

    @Test(dataProvider = "pathProvider")
    void checkThatEOFExceptionIsNotThrown(String path) {
        parser.readFromFile(new File(path));
        //file reading isn't stopped after reaching end of file.
    }

    @DataProvider
    public Object[][] pathProvider() {
        String[][] data = new String[][]{
                {"src/main/resources/andrew-cart.json"},
                {"src/main/resources/eugen-cart.json"}
        };
        return data;
    }

    @Test
    void testFileIsCreated() {
        parser.writeToFile(cart);
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");
        assertTrue(file.exists());
    }
}