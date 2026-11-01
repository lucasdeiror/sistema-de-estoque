package core.usecase;

import core.DTO.CadastroProdutoDTO;
import core.entidades.Produto;
import core.repository.ProdutoRepository;

import java.util.UUID;

public class CadastrarProdutoUseCase {

    private final ProdutoRepository repository;

    public CadastrarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrarProduto(CadastroProdutoDTO dadosProduto) {

        Produto novoProduto = new Produto(
                UUID.randomUUID(),
                dadosProduto.getNome(),
                dadosProduto.getCategoria(),
                dadosProduto.getPreco(),
                dadosProduto.getEstoque()
        );

        repository.salvar(novoProduto);

        return novoProduto;
    }
}