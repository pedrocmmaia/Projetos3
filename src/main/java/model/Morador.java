package model;

public class Morador extends Usuario {
    private  Integer id;
    private Integer usuarioId;
    private Integer apartamentoId;

    public Morador() {
        super();
    }

    public Morador(Integer usuarioId, Integer apartamentoId) {
        this.usuarioId = usuarioId;
        this.apartamentoId = apartamentoId;
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

    public Integer getApartamentoId() {
        return apartamentoId;
    }

    public void setApartamentoId(Integer apartamentoId) {
        this.apartamentoId = apartamentoId;
    }

    @Override
    public String toString() {
        return "Morador [ usuario_id=" + usuarioId + ", apartamento_id=" + apartamentoId + "]";
    }

}