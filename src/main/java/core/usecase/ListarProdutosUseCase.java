package core.usecase;

import core.entidades.Produto;
import core.repository.ProdutoRepository;

import java.util.List;

public class ListarProdutosUseCase {

    private final ProdutoRepository repository;

    public ListarProdutosUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarProdutos() {
        return repository.listar();
    }
}