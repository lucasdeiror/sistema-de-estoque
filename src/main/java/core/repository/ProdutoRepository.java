package core.repository;

import core.entidades.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository {

    void salvar(Produto produto);

    void deletar(UUID id);

    Optional<Produto> buscarPorId(UUID id);

    List<Produto> buscarPorNome(String nome);

    void atualizar(Produto produto);

    List<Produto> listar();
}