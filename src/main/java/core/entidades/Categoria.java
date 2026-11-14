package core.entidades;

import java.util.UUID;

public class Categoria {
    private UUID id;
    private String nome;

    public Categoria() {
    }

    public Categoria(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
