package core.controller;

import core.DTO.BuscaProdutoDTO;
import core.DTO.CadastroProdutoDTO;
import core.entidades.Produto;
import core.usecase.AtualizarProdutoUseCase;
import core.usecase.BuscarProdutoUseCase;
import core.usecase.CadastrarProdutoUseCase;
import core.usecase.DeletarProdutoUseCase;
import core.usecase.ListarProdutosUseCase;

import java.util.List;
import java.util.UUID;

public class ProdutoController {

    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final DeletarProdutoUseCase deletarProdutoUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;

    public ProdutoController(
            CadastrarProdutoUseCase cadastrarProdutoUseCase,
            BuscarProdutoUseCase buscarProdutoUseCase,
            ListarProdutosUseCase listarProdutosUseCase,
            DeletarProdutoUseCase deletarProdutoUseCase,
            AtualizarProdutoUseCase atualizarProdutoUseCase
    ) {
        this.cadastrarProdutoUseCase = cadastrarProdutoUseCase;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.deletarProdutoUseCase = deletarProdutoUseCase;
        this.atualizarProdutoUseCase = atualizarProdutoUseCase;
    }

    public Produto cadastrarProduto(
            String nome,
            String categoria,
            float preco,
            int estoque
    ) {
        CadastroProdutoDTO dadosProduto = new CadastroProdutoDTO(
                nome,
                categoria,
                preco,
                estoque
        );

        return cadastrarProdutoUseCase.cadastrarProduto(dadosProduto);
    }

    public List<Produto> buscarProdutoPorNome(String nome) {
        BuscaProdutoDTO dadosBusca = new BuscaProdutoDTO(nome);

        return buscarProdutoUseCase.buscarProduto(dadosBusca);
    }

    public Produto buscarProdutoPorId(UUID id) {
        BuscaProdutoDTO dadosBusca = new BuscaProdutoDTO(id);

        return buscarProdutoUseCase.buscarProduto(dadosBusca).get(0);
    }

    public List<Produto> listarProdutos() {
        return listarProdutosUseCase.listarProdutos();
    }

    public void deletarProduto(UUID id) {
        deletarProdutoUseCase.deletarProduto(id);
    }

    public Produto atualizarProduto(
            UUID id,
            String nome,
            String categoria,
            float preco,
            int estoque
    ) {
        return atualizarProdutoUseCase.atualizarProduto(
                id,
                nome,
                categoria,
                preco,
                estoque
        );
    }
}