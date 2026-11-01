package core.DTO;

import java.util.UUID;

public class BuscaProdutoDTO {

    private final String nome;
    private final UUID id;

    public BuscaProdutoDTO(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório."
            );
        }

        this.nome = nome.trim();
        this.id = null;
    }

    public BuscaProdutoDTO(UUID id) {

        if (id == null) {
            throw new IllegalArgumentException(
                    "O ID do produto é obrigatório."
            );
        }

        this.nome = null;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public UUID getId() {
        return id;
    }

    public boolean buscaPorId() {
        return id != null;
    }
}