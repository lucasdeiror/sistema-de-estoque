import core.controller.ProdutoController;
import core.repository.ProdutoRepository;
import core.usecase.AtualizarProdutoUseCase;
import core.usecase.BuscarProdutoUseCase;
import core.usecase.CadastrarProdutoUseCase;
import core.usecase.DeletarProdutoUseCase;
import core.usecase.ListarProdutosUseCase;
import infra.repository.ProdutoRepositoryJDBC;
import ui.MenuConsole;

public class Aplplication {

    public static void main(String[] args) {

        ProdutoRepository repository = new ProdutoRepositoryJDBC();

        CadastrarProdutoUseCase cadastrarProdutoUseCase =
                new CadastrarProdutoUseCase(repository);

        BuscarProdutoUseCase buscarProdutoUseCase =
                new BuscarProdutoUseCase(repository);

        ListarProdutosUseCase listarProdutosUseCase =
                new ListarProdutosUseCase(repository);

        DeletarProdutoUseCase deletarProdutoUseCase =
                new DeletarProdutoUseCase(repository);

        AtualizarProdutoUseCase atualizarProdutoUseCase =
                new AtualizarProdutoUseCase(repository);

        ProdutoController produtoController = new ProdutoController(
                cadastrarProdutoUseCase,
                buscarProdutoUseCase,
                listarProdutosUseCase,
                deletarProdutoUseCase,
                atualizarProdutoUseCase
        );

        MenuConsole menu = new MenuConsole(produtoController);

        menu.iniciar();
    }
}