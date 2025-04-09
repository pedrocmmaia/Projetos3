package model;


public class Sindico extends Usuario {



    private Integer id;
    private Integer usuarioId;

    public Sindico() {
        super();
    }

    public Sindico(Integer usuarioId) {
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
        return "Sindico [usario_id=" + usuarioId  + "]";
    }
}