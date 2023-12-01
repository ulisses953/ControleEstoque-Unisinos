package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import error.IdNotFoundException;
import interfaces.CRUD;
import interfaces.SerializeObject;
import model.Stock;
import model.Product;

public class ServiceStock implements CRUD<Product, UUID>, SerializeObject<ServiceStock>{
    private Stock stock;
  
    @Override
    public Product update(Product object, UUID id) throws IdNotFoundException {
        final Integer INDEX = findByIndex(id); 
        List<Product> list = stock.getProducts();

        if (INDEX == null) {
            throw new IdNotFoundException("id not found id:" + id);
        }

        list.add(INDEX, object);
        
        stock.setProducts(list);

        return object;
    }

    @Override
    public Product update(Product object) throws IdNotFoundException {
        return update(object, object.getId());
    }

    @Override
    public Product save(Product object) {
        if (object == null) {
            throw new IllegalArgumentException("object cannot be null");
        }
        
        List<Product> list = stock.getProducts();
        list.add(object);

        stock.setProducts(list);

        return object;
    }

    @Override
    public Product delete(UUID id) throws IdNotFoundException  {
        final Product OBJ =  findById(id);
        List<Product> list = stock.getProducts();

        if (OBJ == null) {
            throw new IdNotFoundException("id not found id:" + id);
        }

        list.remove(OBJ);
        
        stock.setProducts(list);

        return OBJ;
    } 

    @Override
    public Product findById(UUID id) throws IdNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");    
        }
        
        List<Product> list = stock.getProducts();

        for (Product produto : list) {
            if(produto.getId() == id) {
                return produto;
            }
        }
        throw new IdNotFoundException("id not found id:" + id);
    }
    
    public Integer findByIndex(UUID id) throws IdNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        List<Product> list = stock.getProducts();

        for (int i = 0; i < list.size();i++) {
            if(list.get(i).getId() == id) {
                return i;
            }
        }
        throw new IdNotFoundException("id not found id:" + id);
    }
    
    public List<Product> findAll() {
        return stock.getProducts();
    }

    public Product addToStock(UUID id, int amount) throws IdNotFoundException {
        if (amount < 0) return null;
        Product product = findById(id);
        amount += product.getQuantity();
        product.setQuantity(amount);
        return product;
    }

    public Product removeFromStock(UUID id, int amount) throws IdNotFoundException {
        Product product = findById(id);
        if (amount > 0) amount *= -1;
        amount += product.getQuantity();
        if (amount < 0) return null;
        product.setQuantity(amount);
        return product;
    }
    /**
     * 
     * @param product
     * @return true if understocked, false otherwise
     */
    public boolean isLowOnStock(Product product){
        if(product == null){
            throw new IllegalArgumentException("product is null");
        }
        return (product.getQuantity() < product.getMinimumQuantity());
    }
    /**
     * 
     * @return List of understocked products
     */
    public List<Product> seeLowOnStockProducts(){
        List<Product> returnList = new ArrayList<Product>();
        final List<Product> LIST = stock.getProducts();
        for (Product product : LIST) {
            if(isLowOnStock(product)){
                returnList.add(product);
            }
        }
        return returnList;
    }

    //#region get and set
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    //#endregion

    //#region constructors
    public ServiceStock(Stock stock) {
        this.stock = stock;
    }

    public ServiceStock(List<Product> products) {
        this.stock = new Stock(products);
    }
    //#endregion
   
    
}
