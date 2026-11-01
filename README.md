# Sistema de Estoque — Java Puro + Clean Architecture

Projeto de portfólio em **Java puro (sem frameworks, sem banco de dados, sem rede)** — um sistema de controle de estoque via linha de comando (CLI), com o objetivo duplo de:

1. Fechar lacunas de fundamentos de Java (coleções, generics, streams, exceções, Optional, Records) antes de avançar para persistência (JDBC) e Spring Boot.
2. Aplicar **Clean Architecture** na prática, entendendo a separação de camadas e a regra de dependência (tudo aponta para dentro, o `core` não conhece nada de fora).

Segundo projeto de uma sequência de portfólio em Java, após o Consumo de API + Dashboard de Localização (foco em integração HTTP/JSON).

## Objetivos de aprendizado

- **Coleções**: `List`, `Set`, `Map` — quando usar cada uma, e por quê
- **Generics**: interfaces e classes reutilizáveis com tipo parametrizado
- **Streams API** e expressões lambda: filtrar, ordenar, agrupar
- **Tratamento de exceções**: checked vs unchecked, exceções de domínio customizadas
- **Optional**: evitar `null` como retorno de busca
- **Records**: objetos de dado imutáveis e simples
- **Clean Architecture**: separação entre regra de negócio e detalhes técnicos, inversão de dependência

## Arquitetura

```
src/main/java/
  core/
    domain/
      Produto.java                       # entidade — regra de negócio pura
      exception/
        ProdutoNaoEncontradoException.java
        EstoqueInsuficienteException.java
    usecase/
      repository/
        ProdutoRepository.java           # interface — contrato definido pelo core
      CadastrarProdutoUseCase.java
      ListarProdutosUseCase.java
      BuscarProdutoUseCase.java
      RegistrarMovimentacaoUseCase.java
  infra/
    repository/
      InMemoryProdutoRepository.java     # implementação do contrato, usando Map
  application/
    Application.java                     # ponto de entrada, menu CLI
```

### A regra de dependência

`core/` nunca importa nada de `infra/` ou `application/`. A interface `ProdutoRepository` — o contrato de "salvar, buscar, listar produtos" — é definida **dentro do core**; quem a implementa de verdade (`ProdutoRepositoryJDBC`, hoje usando um `Map` em memória) fica em `infra/`. Isso significa que trocar a forma de armazenamento (por exemplo, para persistência real com JDBC/MySQL, numa fase futura) exige mudar apenas a camada `infra/` — o `core` e os casos de uso continuam intactos.

## Tecnologias

- **Java puro** — sem Spring Boot, sem bibliotecas externas de persistência ou DI
- Sem banco de dados — armazenamento em memória via coleções, por decisão de escopo (o foco é fundamentos de linguagem e arquitetura, não persistência)
- Sem interface gráfica — interação via console (CLI)

## Status atual

⬜ Entidade `Produto` e exceções de domínio
⬜ Interface `ProdutoRepository`
⬜ `ProdutoRepositoryJDBC` (implementação com `Map`)
⬜ Casos de uso (cadastrar, listar, buscar, registrar movimentação)
⬜ CLI de interação (`Application`)

## Como rodar

1. Clonar o repositório e abrir no IntelliJ como projeto Maven.
2. Rodar `Application.java` — não requer banco de dados, variável de ambiente ou API key nenhuma.

## Roadmap

Este projeto é propositalmente limitado em escopo (sem banco, sem rede) para isolar o aprendizado de fundamentos de Java e Clean Architecture. Persistência real (JDBC/MySQL) fica para uma fase futura, possivelmente reaproveitando esta mesma estrutura de camadas — trocando apenas a implementação em `infra/`.