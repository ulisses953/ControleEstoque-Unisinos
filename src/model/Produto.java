package model;

import java.util.UUID;

public class Produto {
    private UUID id = UUID.randomUUID();
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private int quantidadeMinima;
    
    
    //#region get and set 
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        if (preco <= 0 ) {
            throw new IllegalArgumentException("O valor do preco e negativo");
        }
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        if (quantidade < 0 ) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        
        this.quantidade = quantidade;
    }
    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }
    public void setQuantidadeMinima(int quantidadeMinima) {
        if (quantidadeMinima <= 0 ) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        this.quantidadeMinima = quantidadeMinima;
    }
    //#endregion
    //#region constructor

    public Produto(String nome, String descricao, double preco, int quantidade, int quantidadeMinima) {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setQuantidade(quantidade);
        setQuantidadeMinima(quantidadeMinima);
    }

    public Produto(){

    }

    //#endregion
    

}
