package core.usecase;

import core.entidades.Produto;
import core.exception.ProdutoNaoEncontradoException;
import core.repository.ProdutoRepository;

import java.util.UUID;

public class AtualizarProdutoUseCase {

    private final ProdutoRepository repository;

    public AtualizarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto atualizarProduto(
            UUID id,
            String nome,
            String categoria,
            float preco,
            int estoque
    ) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "O ID do produto é obrigatório."
            );
        }

        repository.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(
                        "Produto não encontrado."
                ));

        Produto produtoAtualizado = new Produto(
                id,
                nome,
                categoria,
                preco,
                estoque
        );

        repository.atualizar(produtoAtualizado);

        return produtoAtualizado;
    }
}