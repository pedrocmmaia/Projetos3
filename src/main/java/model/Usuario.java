package model;

public class Usuario {
    public int id;   
    public String nome;
    public String email;
    public String senha;
    private enum Tipo{
        Morador, Sindico;
    }

    public Usuario(int id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
}
