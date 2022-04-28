package com.api.cadastro.cadastroms.view.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProdutoRequest {
    @NotBlank(message = "nome não pode estar em branco") @NotEmpty(message = "nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "codigo não pode estar em branco") @NotEmpty(message = "codigo não pode ser vazio")
    private String codigo;
    @Min((long) 0.01) @NotNull(message = "preco deve conter um valor")
    private double preco;
    @NotNull(message = "quantidadeEstoque não pode estar em branco")
    @Min(0)
    private int quantidadeEstoque;
    
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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
