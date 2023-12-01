package test.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.Product;

public class ProductTest {
    
    @Test
    void testSetMinimumQuantityNegative() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setMinimumQuantity(-1);
        },() -> "");
    }

    @Test
    void testSetPriceNegative() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setPrice(-1);
        },() -> " ");
    }

    @Test
    void testSetPriceZero() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setPrice(0);
        },() -> " ");
    }

    @Test
    void testSetNameIsNull() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setName(null);
        },() -> " ");
    }
    @Test
    void testSetNameIsLenghtZero() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setName("");
        },() -> " ");
    }

    @Test
    void testSetNameIsLenghtThree() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setName("123");
        },() -> "");
    }

    @Test
    void testSetId() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setId(null);
        },() -> "");
    }
    

    @Test
    void testsetQuantity() {
        Product product = new Product();

        assertThrows(IllegalArgumentException.class, ()-> {
            product.setQuantity(-10);
        },() -> "");
    }
}
