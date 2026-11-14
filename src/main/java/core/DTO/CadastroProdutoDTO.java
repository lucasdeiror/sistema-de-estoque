package core.DTO;

import core.entidades.Categoria;

import java.math.BigDecimal;

public class CadastroProdutoDTO {

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal preco;
    private final int estoque;

    public CadastroProdutoDTO(
            String nome,
            Categoria categoria,
            BigDecimal preco,
            int estoque
    ) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }
}