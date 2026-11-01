package infra.repository;

import core.entidades.Produto;
import core.exception.CadastroInvalidoException;
import core.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ProdutoRepositoryJDBC implements ProdutoRepository {

    private final Map<UUID, Produto> produtos = new HashMap<>();

    @Override
    public void salvar(Produto novoProduto) {

        if (produtos.containsKey(novoProduto.getId())) {
            throw new CadastroInvalidoException("Produto já cadastrado.");
        }

        produtos.put(novoProduto.getId(), novoProduto);
    }

    @Override
    public void deletar(UUID id) {
        produtos.remove(id);
    }

    @Override
    public Optional<Produto> buscarPorId(UUID id) {
        return Optional.ofNullable(produtos.get(id));
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {

        List<Produto> encontrados = new ArrayList<>();

        for (Produto produto : produtos.values()) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(produto);
            }
        }

        return encontrados;
    }

    @Override
    public void atualizar(Produto produto) {

        if (!produtos.containsKey(produto.getId())) {
            throw new CadastroInvalidoException(
                    "Não é possível atualizar um produto inexistente."
            );
        }

        produtos.put(produto.getId(), produto);
    }

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(produtos.values());
    }
}