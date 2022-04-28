package com.api.cadastro.cadastroms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produto")
public class Produto {
    @Id
    private String id;
    private String nome;
    private String codigo;
    private double preco;
    private int quantidadeEstoque;
    
    public boolean removerEstoque (int quantidadeRemover) {
        if ((this.quantidadeEstoque - quantidadeRemover) < 0) {
            return false;
        }
    
        this.quantidadeEstoque -= quantidadeRemover;

        return true;

    }
    
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }


    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
}
