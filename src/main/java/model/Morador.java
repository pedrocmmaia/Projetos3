package model;

public class Morador extends Usuario {

    public Morador(int id, String nome, String email, String senha, String telefone){
        super(id, nome, email, senha, telefone, Tipo.Morador);
    }
}