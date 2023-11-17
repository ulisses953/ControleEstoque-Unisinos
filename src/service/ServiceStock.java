package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import error.IdNotFound;
import interfaces.InterfaceCRUD;
import model.Stock;
import model.Product;

public class ServiceStock implements InterfaceCRUD<Product, UUID>{
    private Stock stock;
  
    @Override
    public Product update(Product object, UUID id) throws IdNotFound {
        final Integer INDEX = findByIndex(id); 
        List<Product> list = stock.getProducts();

        if (INDEX == null) {
            throw new IdNotFound("id not found id:" + id);
        }

        list.add(INDEX, object);
        
        stock.setProducts(list);

        return object;
    }

    @Override
    public Product update(Product object) throws IdNotFound {
        return update(object, object.getId());
    }

    @Override
    public Product save(Product object) {
        List<Product> list = stock.getProducts();
        list.add(object);

        stock.setProducts(list);

        return object;
    }

    @Override
    public Product delete(UUID id) throws IdNotFound  {
        final Product OBJ =  findById(id);
        List<Product> list = stock.getProducts();

        if (OBJ == null) {
            throw new IdNotFound("id not found id:" + id);
        }

        list.remove(OBJ);
        
        stock.setProducts(list);

        return OBJ;
    } 

    @Override
    public Product findById(UUID id) throws IdNotFound {
        List<Product> list = stock.getProducts();

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

    public List<Product> seeUnderstockedProducts(){
        List<Product> returnList = new ArrayList<Product>();
        final List<Product> LIST = stock.getProducts();
        for (Product product : LIST) {
            if(product.getQuantity() < product.getMinimumQuantity()){
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
