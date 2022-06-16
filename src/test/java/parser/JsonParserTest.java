package parser;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;

import java.io.File;



public class JsonParserTest {
    private static JsonParser parser;
    private static Cart cart;

    @BeforeAll
    static void beforeAll() {
        parser = new JsonParser();
    }

    @BeforeEach
    void createCart() {
        cart = new Cart("test");
        RealItem item = new RealItem();
        item.setName("Cat Food");
        item.setPrice(20);
        item.setWeight(2);
        cart.addRealItem(item);
    }

    @AfterAll
    static void deleteFile() {
        new File("src/main/resources/" + cart.getCartName() + ".json").delete();
    }

    @Test
    void checkIfWrittenAndReadDataIsCorrect() {
        parser.writeToFile(cart);
        Cart cartFromFile = parser.readFromFile(new File("src/main/resources/" + cart.getCartName() + ".json"));
        Assertions.assertEquals(cart.getCartName(), cartFromFile.getCartName());
        Assertions.assertAll("carts should match",
                () -> Assertions.assertEquals(cart.getCartName(), cartFromFile.getCartName(), "Names do not match"),
                () -> Assertions.assertEquals(cart.getTotalPrice(), cartFromFile.getTotalPrice(), "Prices do not match")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "src/main/resources/cart.json",
            "src/andrew-cart.json",
            "src/eugen-cart.json",
            "src/main/test.json",
            "src/main/resources/test2.json",
    })
    void checkIfThrowsNoSuchFileException(String path) {
        Assertions.assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File(path)), "Wrong path");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "src/main/resources/andrew-cart.json",
            "src/main/resources/eugen-cart.json",
            "src/main/resources/test.json",
    })
    void checkThatNoExceptionsAreThrown(String path) {
        Assertions.assertDoesNotThrow(() -> parser.readFromFile(new File(path)), "Reading failed");
        //file reading isn't stopped after reaching end of file.
    }


    @Disabled
    @Test
    void testFileIsCreated() {
        parser.writeToFile(cart);
        File file = new File("src/main/resources/" + cart.getCartName() + ".json");
        Assertions.assertTrue(file.exists());
    }
}