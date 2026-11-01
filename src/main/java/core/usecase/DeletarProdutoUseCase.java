package core.usecase;

import core.entidades.Produto;
import core.exception.ProdutoNaoEncontradoException;
import core.repository.ProdutoRepository;

import java.util.UUID;

public class DeletarProdutoUseCase {

    private final ProdutoRepository repository;

    public DeletarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void deletarProduto(UUID id) {

        Produto produto = repository.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(
                        "Produto não encontrado."
                ));

        repository.deletar(produto.getId());
    }
}