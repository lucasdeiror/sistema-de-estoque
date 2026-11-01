package core.usecase;

import core.DTO.BuscaProdutoDTO;
import core.entidades.Produto;
import core.exception.ProdutoNaoEncontradoException;
import core.repository.ProdutoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BuscarProdutoUseCase {

    private final ProdutoRepository repository;

    public BuscarProdutoUseCase(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> buscarProduto(BuscaProdutoDTO dadosBusca) {

        if (dadosBusca == null) {
            throw new IllegalArgumentException(
                    "Os dados da busca são obrigatórios."
            );
        }

        if (dadosBusca.buscaPorId()) {
            return buscarPorId(dadosBusca);
        }

        return buscarPorNome(dadosBusca);
    }

    private List<Produto> buscarPorId(BuscaProdutoDTO dadosBusca) {

        Optional<Produto> produto =
                repository.buscarPorId(dadosBusca.getId());

        if (produto.isEmpty()) {
            throw new ProdutoNaoEncontradoException(
                    "Nenhum produto encontrado com o ID informado."
            );
        }

        return Collections.singletonList(produto.get());
    }

    private List<Produto> buscarPorNome(BuscaProdutoDTO dadosBusca) {

        List<Produto> produtos =
                repository.buscarPorNome(dadosBusca.getNome());

        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontradoException(
                    "Nenhum produto encontrado com o nome informado."
            );
        }

        return produtos;
    }
}