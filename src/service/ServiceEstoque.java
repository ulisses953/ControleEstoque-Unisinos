package service;

import java.util.List;
import java.util.UUID;

import interfaces.InterfaceCRUD;
import model.Estoque;

public class ServiceEstoque implements InterfaceCRUD{
    private Estoque estoque;

    @Override
    public Object update(Object objeto, UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Object update(Object objeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Object salvar(Object obejeto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public Object excluir(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public Object findbyid(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findbyid'");
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
    //#region constructors

    public ServiceEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    //#endregion
    
}
