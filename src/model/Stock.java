package model;

import java.util.List;
import java.util.UUID;

public class Stock extends AbstractSerializableObject<Stock>{
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

    }
    //#endregion

}
