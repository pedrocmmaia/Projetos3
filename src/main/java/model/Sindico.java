package model;


public class Sindico extends Usuario {

    public Sindico(int usuario_id) {
        super();
        this.setId(usuario_id);
    }

    public Sindico(Integer id, String nome, String email,String senha, String telefone, TipoUsuario tipoUsuario) {
        super(id, nome, email, senha, telefone, tipoUsuario);
        this.id = id;
    }


    @Override
    public String toString() {
        return "Sindico{" +
                "id=" + getId() +
                '}';
    }
}