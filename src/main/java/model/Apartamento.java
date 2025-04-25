package model;

public class Apartamento {
    private int id;
    private int numero;
    private int andar;
    private Bloco bloco;

    public Apartamento(int id, int numero, int andar, Bloco bloco) {
        this.id = id;
        this.numero = numero;
        this.andar = andar;
        this.bloco = bloco;
    }

    public Apartamento(int id, int numero, int andar, int blocoId) {
        this.id = id;
        this.numero = numero;
        this.andar = andar;
        this.bloco = new Bloco(blocoId, null);
    }

    public Apartamento(){

    }
    // Getters e Setters
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

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    @Override
    public String toString() {
        return "Apartamento{" +
                "id=" + id +
                ", numero=" + numero +
                ", andar=" + andar +
                ", blocoId=" + bloco +
                '}';
    }
}
