package core.entidades;

import core.exception.CadastroInvalidoException;

import java.util.UUID;

public class Produto {
    private UUID id;
    private String nome,categoria;
    private float preco;
    private int estoque;

    public Produto() {
    }

    public Produto(UUID id, String nome, String categoria, float preco, int estoque) {

        if (nome == null || nome.isBlank()) {
            throw new CadastroInvalidoException("Nome é obrigatório.");
        }

        if (categoria == null || categoria.isBlank()) {
            throw new CadastroInvalidoException("Categoria é obrigatória.");
        }

        if (preco < 0) {
            throw new CadastroInvalidoException("Preço não pode ser negativo.");
        }

        if (estoque < 0) {
            throw new CadastroInvalidoException("Estoque não pode ser negativo.");
        }


        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void adicionarEstoque(int quantidade) {

        if (quantidade <= 0 ) {
            throw new IllegalArgumentException(
                    "Quantidade deve ser positiva"
            );
        }

        this.estoque += quantidade;
    }

    public void removerEstoque(int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser positiva."
            );
        }

        if (quantidade > estoque) {
            throw new IllegalArgumentException(
                    "Estoque insuficiente."
            );
        }

        this.estoque -= quantidade;
    }



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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float valor) {
        this.preco = valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
