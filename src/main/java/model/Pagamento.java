package model;

import java.time.LocalDate;

public class Pagamento {
    private int id;
    private TipoPagamento tipo;
    private float valor;
    private LocalDate dataVencimento;

    public Pagamento(){};

    public Pagamento(int id, TipoPagamento tipo, float valor,
                     LocalDate dataVencimento) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
    }
    public enum TipoPagamento {
    CONDOMINIAL,
    IPTU,
    EXTRA
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public TipoPagamento getTipo() {
        return tipo;
    }
    public void setTipo(TipoPagamento tipo) {
        this.tipo = tipo;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
     @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", valor=" + valor +
                ", dataVencimento=" + dataVencimento +
                '}';
    }
}
