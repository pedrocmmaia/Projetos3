package model;

public class Sindico extends Usuario {

    public Sindico(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone, Tipo.Sindico);
    }
}
