package model;

import java.io.File;
import java.util.List;
import java.util.UUID;

import interfaces.SerializableObject;

public class Stock implements SerializableObject<Stock>{
    private UUID id = UUID.randomUUID();
    private List<Product> products;

    
    //#region get and set 
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> produtos) {
        this.products = produtos;
    }
    //#endregion

    //#region constructor 
    public Stock(List<Product> produtos) {
        this.products = produtos;
    }

    public Stock() {
        Config config = Config.getInstance();
        File arquivo = new File(config.getSerializeRootPath() + "\\" +  this.getClass().getName() + ".ser");
        if (arquivo.exists() && (config.isSerializeEverything() || config.isSerializeStock())) {
            Stock serializedStock = getSavedObject();
            this.id = serializedStock.getId();
            this.products = serializedStock.getProducts();
        }
        
        if(config.isSerializeEverything() || config.isSerializeStock()) {
            this.saveObject();
        }
    }
    //#endregion
}
