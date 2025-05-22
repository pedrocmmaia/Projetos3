package model;

public class Administrador extends Usuario {

    private Integer id;
    private Integer usuarioId;

    public Administrador() {
        super();
    }

    public Administrador(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    @Override
    public String toString() {
        return "Administrador [ administrador_id=" + usuarioId  + "]";
    }

}