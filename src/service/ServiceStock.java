package service;

import java.util.List;
import java.util.UUID;

import error.IdNotFound;
import interfaces.InterfaceCRUD;
import model.Stock;
import model.Product;

public class ServiceStock implements InterfaceCRUD<Product, UUID>{
    private Stock stock;
    
    public Product update(Product object, UUID id) throws IdNotFound {
        object.setId(id);

        return update(object);
    }

    
    public Product update(Product object) throws IdNotFound {
        Product result = findById(object.getId());

        result = object;

        return result;
    }

    
    public Product save(Product object) throws IllegalArgumentException{
        if (object == null) {
            throw new IllegalArgumentException();   
        }

        List<Product> list = stock.getProducts();
        list.add(object);

        stock.setProducts(list);

        return object;
    }

    
    public Product delete(UUID id) throws IdNotFound  {
        final Product OBJ = findById(id);

        List<Product> list = stock.getProducts();

        list.remove(OBJ);
        
        stock.setProducts(list);

        return OBJ;
    } 

    
    public Product findById(UUID id) throws IdNotFound,IllegalArgumentException {
        List<Product> list = stock.getProducts();

        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        for (Product produto : list) {
            if(produto.getId() == id) {
                return produto;
            }
        }
        throw new IdNotFound("id not found id:" + id);
    }
    
    public Integer findByIndex(UUID id) throws IdNotFound {
        List<Product> list = stock.getProducts();

        for (int i = 0; i < list.size();i++) {
            if(list.get(i).getId() == id) {
                return i;
            }
        }
        throw new IdNotFound("id not found id:" + id);
    }
    
    public List<Product> findAll() {
        return stock.getProducts();
    }

    public Product addToStock(UUID id, int amount) throws IdNotFound {
        if (amount < 0) return null;
        Product product = findById(id);
        amount += product.getQuantity();
        product.setQuantity(amount);
        return product;
    }

    public Product removeFromStock(UUID id, int amount) throws IdNotFound {
        Product product = findById(id);
        if (amount > 0) amount *= -1;
        amount += product.getQuantity();
        if (amount < 0) return null;
        product.setQuantity(amount);
        return product;
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
