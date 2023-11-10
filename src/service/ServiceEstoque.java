package service;

import java.util.List;
import java.util.UUID;

import interfaces.InterfaceCRUD;
import model.Estoque;
import model.Produto;

public class ServiceEstoque implements InterfaceCRUD<Produto>{
    private Estoque estoque;

    @Override
    public Produto update(Produto objeto, UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public Produto update(Produto objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public Produto salvar(Produto obejeto) {
        List<Produto> list = estoque.getProdutos();

        list.add(obejeto);

        estoque.setProdutos(list);

        return obejeto;
    }
    @Override
    public Produto excluir(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }
    @Override
    public Produto findbyid(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findbyid'");
    }
    @Override
    public List<Produto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
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
