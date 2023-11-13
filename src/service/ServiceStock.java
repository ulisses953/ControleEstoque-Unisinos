package service;

import java.util.List;
import java.util.UUID;

import error.IdNotFound;
import interfaces.InterfaceCRUD;
import model.Stock;
import model.Product;

public class ServiceEstoque implements InterfaceCRUD<Product, UUID>{
    private Stock estoque;
  
    @Override
    public Product update(Product object, UUID id) {
        final Integer INDEX = findByIndex(id); 
        List<Product> list = estoque.getProducts();

        if (INDEX == null) {
            throw new IdNotFound("id not found id:" + id);
        }

        list.add(INDEX, object);
        
        estoque.setProducts(list);

        return object;
    }

    @Override
    public Product update(Product object) {
        return update(object, object.getId());
    }

    @Override
    public Product save(Product object) {
        List<Product> list = estoque.getProducts();
        list.add(object);

        estoque.setProducts(list);

        return object;
    }

    @Override
    public Product delete(UUID id)  {
        final Product OBJ =  findById(id);
        List<Product> list = estoque.getProducts();

        if (OBJ == null) {
            throw new IdNotFound("id not found id:" + id);
        }

        list.remove(OBJ);
        
        estoque.setProducts(list);

        return OBJ;
    } 

    @Override
    public Product findById(UUID id) {
        List<Product> list = estoque.getProducts();

        for (Product produto : list) {
            if(produto.getId() == id) {
                return produto;
            }
        }
        throw new IdNotFound("id not found id:" + id);
    }
    
    public Integer findByIndex(UUID id) {
        List<Product> list = estoque.getProducts();

        for (int i = 0; i < list.size();i++) {
            if(list.get(i).getId() == id) {
                return i;
            }
        }
        throw new IdNotFound("id not found id:" + id);
    }
    
    public List<Product> findAll() {
        return estoque.getProducts();
    }
    //#region get and set
    public Stock getEstoque() {
        return estoque;
    }
    public void setEstoque(Stock estoque) {
        this.estoque = estoque;
    }
    //#endregion

    //#region constructors
    public ServiceEstoque(Stock estoque) {
        this.estoque = estoque;
    }

    public ServiceEstoque(List<Product> produtos) {
        this.estoque = new Stock(produtos);
    }
    //#endregion
   
    
}
