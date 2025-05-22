package model;

public class Morador extends Usuario {
    private Integer id;
    private Apartamento apartamento;

    public Morador(int id, String nome, String email, String senha, String telefone, TipoUsuario tipo, Apartamento apartamento) {
        super(id,nome, email, senha, telefone, String.valueOf(tipo));
        this.apartamento = apartamento;
    }

    public Morador(int usuarioId, Apartamento apartamento) {
        this.setId(usuarioId); // do Usuario
        this.apartamento = apartamento;
    }

    public Morador(int usuarioId, int apartamentoId) {
        this.setId(usuarioId); // do Usuario
        this.apartamento = new Apartamento();
        this.apartamento.setId(apartamentoId);
    }

    public Morador(){

    }
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoradorId() {
        return id;
    }

    public void setMoradorId(Integer id) {
        this.id = id;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    @Override
    public String toString() {
        return "Morador{" +
                "moradorId=" + id +
                ", usuarioId=" + getId() +
                ", nome=" + getNome() +
                ", apartamento=" + apartamento +
                '}';
    }
}
