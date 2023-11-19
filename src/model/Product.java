package model;

import java.util.UUID;

public class Product extends AbstractSerializableObject<Product> {
    private UUID id = UUID.randomUUID();
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int minimumQuantity;
    
    //#region get and set 
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
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
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getMinimumQuantity() {
        return minimumQuantity;
    }
    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }
    //#endregion
    
    //#region constructor

    public Product(String name, String description, double price, int quantity, int minimumQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
    }

    public Product(){
        
    }

    //#endregion
    

}
