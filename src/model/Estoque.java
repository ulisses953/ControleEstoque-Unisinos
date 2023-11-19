package model;

import java.util.List;
import java.util.UUID;

public class Estoque extends AbstractSerializableObject<Estoque>{
    private UUID id = UUID.randomUUID();
    private List<Produto> produtos;
    
    //#region get and set 
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    //#endregion

    //#region constructor 
    public Estoque(List<Produto> produtos) {
        this.produtos = produtos;
    }
    public Estoque() {

    }
    //#endregion

}
