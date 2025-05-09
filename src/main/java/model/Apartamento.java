package model;

public class Apartamento {
    private int id;
    private int numero;
    private int andar;
    private int blocoId;

    public Apartamento(int id, int numero, int andar, int blocoId) {
        this.id = id;
        this.numero = numero;
        this.andar = andar;
        this.blocoId = blocoId;
    }

    public Apartamento() {

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

    @Override
    public String toString() {
        return "Apartamento{" +
                "id=" + id +
                ", numero=" + numero +
                ", andar=" + andar +
                ", blocoId=" + blocoId +
                '}';
    }
}
