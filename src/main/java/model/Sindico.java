package model;


public class Sindico extends Usuario {

    public Sindico() {
        super();
    }

    public Sindico(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, telefone, TipoUsuario.SINDICO);
    }

    @Override
    public String toString() {
        return "Sindico{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                '}';
    }
}