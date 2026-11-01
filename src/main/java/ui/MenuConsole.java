package ui;

import core.controller.ProdutoController;
import core.entidades.Produto;
import core.exception.ProdutoNaoEncontradoException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuConsole {

    private final Scanner input;
    private final ProdutoController produtoController;

    public MenuConsole(ProdutoController produtoController) {
        this.input = new Scanner(System.in);
        this.produtoController = produtoController;
    }

    public void iniciar() {

        System.out.println("==================================");
        System.out.println("Bem-vindo ao sistema de estoque");
        System.out.println("==================================");

        int decisao;

        do {
            exibirMenu();

            decisao = lerInteiro("Escolha uma opção: ");

            switch (decisao) {
                case 1 -> cadastrarProduto();
                case 2 -> deletarProduto();
                case 3 -> atualizarProduto();
                case 4 -> buscarProduto();
                case 5 -> listarProdutos();
                case 0 ->
                        System.out.println("Encerrando o sistema...");
                default ->
                        System.out.println("Opção inválida.");
            }

        } while (decisao != 0);

        System.out.println("Sistema fechado.");
    }

    private void exibirMenu() {

        System.out.println();
        System.out.println("[1] Cadastrar novo produto");
        System.out.println("[2] Excluir produto");
        System.out.println("[3] Atualizar produto");
        System.out.println("[4] Buscar produto");
        System.out.println("[5] Listar todos os produtos");
        System.out.println("[0] Sair do sistema");
    }

    private void cadastrarProduto() {

        System.out.println("\n=== Cadastro de produto ===");

        try {
            String nome = lerTexto("Nome: ");
            String categoria = lerTexto("Categoria: ");
            float preco = lerFloat("Preço: ");
            int estoque = lerInteiro("Estoque: ");

            Produto produto = produtoController.cadastrarProduto(
                    nome,
                    categoria,
                    preco,
                    estoque
            );

            System.out.println("\nProduto cadastrado com sucesso!");
            exibirProduto(produto);

        } catch (IllegalArgumentException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }

    private void buscarProduto() {

        System.out.println("\n=== Buscar produto ===");
        System.out.println("[1] Buscar por nome");
        System.out.println("[2] Buscar por ID");

        int tipoBusca = lerInteiro("Escolha o tipo de busca: ");

        try {
            switch (tipoBusca) {
                case 1 -> {
                    String nome = lerTexto("Nome do produto: ");

                    List<Produto> produtos =
                            produtoController.buscarProdutoPorNome(nome);

                    for (Produto produto : produtos) {
                        exibirProduto(produto);
                    }
                }

                case 2 -> {
                    UUID id = lerUUID("ID do produto: ");

                    Produto produto = produtoController.buscarProdutoPorId(id);

                    exibirProduto(produto);
                }

                default -> System.out.println("Tipo de busca inválido.");
            }

        } catch (IllegalArgumentException | ProdutoNaoEncontradoException e) {
            System.out.println("Busca não realizada: " + e.getMessage());
        }
    }

    private void listarProdutos() {

        System.out.println("\n=== Produtos cadastrados ===");

        List<Produto> produtos = produtoController.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto produto : produtos) {
            exibirProduto(produto);
        }
    }

    private void deletarProduto() {

        System.out.println("\n=== Excluir produto ===");

        UUID id = lerUUID("ID do produto que deseja excluir: ");

        try {
            produtoController.deletarProduto(id);
            System.out.println("Produto excluído com sucesso.");

        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("Não foi possível excluir: " + e.getMessage());
        }
    }

    private void atualizarProduto() {
        System.out.println("\n=== Atualizar produto ===");

        try {
            UUID id = lerUUID("ID do produto que deseja atualizar: ");

            String nome = lerTexto("Novo nome: ");
            String categoria = lerTexto("Nova categoria: ");
            float preco = lerFloat("Novo preço: ");
            int estoque = lerInteiro("Novo estoque: ");

            Produto produtoAtualizado = produtoController.atualizarProduto(
                    id,
                    nome,
                    categoria,
                    preco,
                    estoque
            );

            System.out.println("\nProduto atualizado com sucesso!");
            exibirProduto(produtoAtualizado);

        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(
                    "Não foi possível atualizar: " + e.getMessage()
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Dados inválidos: " + e.getMessage());
        }
    }

    private void exibirProduto(Produto produto) {

        System.out.println("------------------------------");
        System.out.println("ID: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Categoria: " + produto.getCategoria());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Estoque: " + produto.getEstoque());
        System.out.println("------------------------------");
    }

    private String lerTexto(String mensagem) {

        System.out.print(mensagem);

        return input.nextLine().trim();
    }

    private int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String valor = input.nextLine().trim();

            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private float lerFloat(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String valor = input.nextLine()
                    .trim()
                    .replace(',', '.');

            try {
                return Float.parseFloat(valor);
            } catch (NumberFormatException e) {
                System.out.println("Digite um preço válido.");
            }
        }
    }

    private UUID lerUUID(String mensagem) {

        while (true) {

            String valor = lerTexto(mensagem);

            try {
                return UUID.fromString(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Digite um UUID válido.");
            }
        }
    }
}