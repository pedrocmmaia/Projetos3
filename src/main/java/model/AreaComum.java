package model;

import java.util.Date;

public class AreaComum {
    private int id;
    private String nome;
    private boolean disponibilidade;

    public AreaComum() {}

    public AreaComum(String nome, boolean disponibilidade) {
        this.nome = nome;
        this.disponibilidade = disponibilidade;
    }

    public AreaComum(int id, String nome, boolean disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.disponibilidade = disponibilidade;
    }

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

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public boolean verificarDisponibilidade(Date data) {
        // Aqui você pode adicionar lógica futura para verificar a disponibilidade com base em reservas
        return disponibilidade;
    }

    @Override
    public String toString() {
        return "Área Comum {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", disponibilidade=" + disponibilidade +
                '}';
    }
}
