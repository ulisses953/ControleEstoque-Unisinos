package model;

import java.util.UUID;

import interfaces.SerializeObject;
import net.bytebuddy.implementation.bytecode.Throw;

public class Product implements SerializeObject<Product> {
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int minimumQuantity;

    // #region get and set
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 5 ) {
            throw new IllegalArgumentException();
        }

        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0 || price == 0) {
            throw new IllegalArgumentException("price not negative or zero");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0 ) {
            throw new IllegalArgumentException("quantity not negative");
        }
        this.quantity = quantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
          if (minimumQuantity < 0 ) {
            throw new IllegalArgumentException("minimumQuantity not negative");
        }

        this.minimumQuantity = minimumQuantity;
    }
    // #endregion

    // #region constructor

    public Product(String name, String description, double price, int quantity, int minimumQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product [id=" + id.toString() + ", name=" + name + ", description=" + description + ", price=" + price
                + ", quantity=" + quantity + ", minimumQuantity=" + minimumQuantity + "]";
    }

    

    // #endregion
}
