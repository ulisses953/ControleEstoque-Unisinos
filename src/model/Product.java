package model;

import java.io.File;
import java.util.UUID;

import interfaces.SaveableObject;

public class Product implements SaveableObject<Product> {
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
        Config config = Config.getInstance();
        File arquivo = new File(config.getSerializeRootPath() + "\\" +  this.getClass().getName() + ".ser");
        if (arquivo.exists() && config.isSerializeEverything()) {
            Product oldProduct = getSavedObject();
            this.name = oldProduct.getName();
            this.description = oldProduct.getDescription();
            this.price = oldProduct.getPrice();
            this.quantity = oldProduct.getQuantity();
            this.minimumQuantity = oldProduct.getMinimumQuantity();
        }
    }

    //#endregion
}
