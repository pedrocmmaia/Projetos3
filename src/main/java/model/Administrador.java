package model;

public class Administrador extends Usuario {

    private Integer id;
    private Integer usuarioId;

    public Administrador() {
        super();
    }

    public Administrador(Integer id, String nome, String email, String senha, String telefone, TipoUsuario tipoUsuario) {
        super(id, nome, email, senha, telefone, tipoUsuario);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + getId() +
                ", nome=" + getNome() +
                '}';
    }
}