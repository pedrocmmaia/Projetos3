package model;

public class Bloco {
    private int id;
    private String nome;

    // Construtor sem ID (para inserção)
    public Bloco(String nome) {
        this.nome = nome;
    }

    // Construtor com ID (para leitura do banco)
    public Bloco(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Bloco { ID: " + id + ", Nome: '" + nome + "' }";
    }
}
