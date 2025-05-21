package model;

public class Apartamento {
    private int id;
    private int numero;
    private int andar;
    private int blocoId;
    private Integer morador_responsavel_id;

    public Apartamento(int id, int numero, int andar, int blocoId, Integer morador_responsavel_id) {
        this.id = id;
        this.numero = numero;
        this.andar = andar;
        this.blocoId = blocoId;
        this.morador_responsavel_id = morador_responsavel_id;
    }

    public Apartamento() {

    }

    public Apartamento(int apartamentoId, int apartamentoNumero, int apartamentoAndar, Bloco bloco) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getBlocoId() {
        return blocoId;
    }

    public void setBlocoId(int blocoId) {
        this.blocoId = blocoId;
    }

    public Integer getMorador_responsavel_id() {
        return morador_responsavel_id;
    }

    public void setMorador_responsavel_id(Integer morador_responsavel_id) {
        this.morador_responsavel_id = morador_responsavel_id;
    }

    @Override
    public String toString() {
        return "Apartamento{" +
                "id=" + id +
                ", numero=" + numero +
                ", andar=" + andar +
                ", blocoId=" + blocoId +
                ", morador_responsavel_id=" + morador_responsavel_id +
                '}';
    }
}
