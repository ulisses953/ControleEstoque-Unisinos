package service;

import java.util.List;
import java.util.UUID;

import error.IdNotFound;
import interfaces.InterfaceCRUD;
import model.Estoque;
import model.Produto;

public class ServiceEstoque implements InterfaceCRUD<Produto, UUID>{
    private Estoque estoque;
  
    @Override
    public Produto update(Produto object, UUID id) throws IdNotFound {
        final Integer INDEX = findByIndex(id); 
        List<Produto> list = estoque.getProdutos();

        if (INDEX == null) {
            throw new IdNotFound("id not found id:" + id);
        }

        list.add(INDEX, object);
        
        estoque.setProdutos(list);

        return object;
    }

    @Override
    public Produto update(Produto object) throws IdNotFound {
        return update(object, object.getId());
    }

    @Override
    public Produto save(Produto object) {
        List<Produto> list = estoque.getProdutos();
        list.add(object);

        estoque.setProdutos(list);

        return object;
    }

    @Override
    public Produto delete(UUID id) throws IdNotFound {
        final Produto OBJ =  findById(id);
        List<Produto> list = estoque.getProdutos();

        if (OBJ == null) {
            throw new IdNotFound("id not found id:" + id);
        }

        list.remove(OBJ);
        
        estoque.setProdutos(list);

        return OBJ;
    } 

    @Override
    public Produto findById(UUID id) throws IdNotFound {
        List<Produto> list = estoque.getProdutos();

        for (Produto produto : list) {
            if(produto.getId() == id) {
                return produto;
            }
        }
        throw new IdNotFound("id not found id:" + id);
    }
    
    public Integer findByIndex(UUID id) throws IdNotFound {
        List<Produto> list = estoque.getProdutos();

        for (int i = 0; i < list.size();i++) {
            if(list.get(i).getId() == id) {
                return i;
            }
        }
        throw new IdNotFound("id not found id:" + id);
    }
    
    public List<Produto> findAll() {
        return estoque.getProdutos();
    }
    //#region get and set
    public Estoque getEstoque() {
        return estoque;
    }
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    //#endregion

    //#region constructors
    public ServiceEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public ServiceEstoque(List<Produto> produtos) {
        this.estoque = new Estoque(produtos);
    }
    //#endregion
   
    
}
