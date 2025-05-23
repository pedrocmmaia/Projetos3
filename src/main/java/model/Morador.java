package model;

public class Morador extends Usuario {

    private Apartamento apartamento;

    public Morador(int id, String nome, String email, String senha, String telefone, TipoUsuario tipo, Apartamento apartamento) {
        super(id, nome, email, senha, telefone, String.valueOf(tipo));
        this.apartamento = apartamento;
    }

    public Morador(int usuarioId, Apartamento apartamento) {
        super();
        this.setId(usuarioId);
        this.apartamento = apartamento;
    }

    public Morador(int usuarioId, int apartamentoId) {
        super();
        this.setId(usuarioId);
        this.apartamento = new Apartamento();
        this.apartamento.setId(apartamentoId);
    }

    public Morador() {
        super();
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
                "usuarioId=" + getId() +
                ", nome=" + getNome() +
                ", apartamento=" + apartamento +
                '}';
    }
}
