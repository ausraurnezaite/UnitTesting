package shop;

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
        cart.addRealItem(item);
        cart.addRealItem(item);
        Assertions.assertEquals((item.getPrice() * 1.2) * 2, cart.getTotalPrice());
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
