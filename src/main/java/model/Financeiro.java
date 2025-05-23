package model;

public class Financeiro {

    private int id;
    private String nome;
    private String email;
    private boolean responsavel;
    private int apartamentoId;
    private Apartamento apartamento;

    public Financeiro(int id, String nome, String email, boolean responsavel, Apartamento apartamento) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.responsavel = responsavel;
    this.apartamento = apartamento;
}

    public int getApartamentoId() {
        return apartamentoId;
    }
    public void setApartamentoId(int apartamentoId) {
        this.apartamentoId = apartamentoId;
    }
    public String getEmail() {
        return email;
    }public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isResponsavel() {
        return responsavel;
    }
    public void setResponsavel(boolean responsavel) {
        this.responsavel = responsavel;
    }

    
}

