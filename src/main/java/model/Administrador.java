package model;

public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, telefone, TipoUsuario.ADMINISTRADOR);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                '}';
    }
}