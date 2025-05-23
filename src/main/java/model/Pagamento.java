package model;

import java.time.LocalDateTime;

public class Pagamento {
    private int id;
    private TipoPagamento tipo;
    private float valor;
    private LocalDateTime dataVencimento;
    private StatusPagamento status;

    public Pagamento(){};

    public Pagamento(int id, TipoPagamento tipo, float valor,
                     LocalDateTime dataVencimento, StatusPagamento status) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }   
    public enum TipoPagamento {
    CONDOMINIAL,
    IPTU,
    EXTRA
    }

    public enum StatusPagamento {
    PAGO,
    PENDENTE,
    ATRASADO
    }
    public void registrarPagamento() {
        this.status = StatusPagamento.PAGO;
    }

    public StatusPagamento verificarStatus() {
        if (status != StatusPagamento.PAGO && LocalDateTime.now().isAfter(dataVencimento)) {
            status = StatusPagamento.ATRASADO;
        }
        return status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public StatusPagamento getStatus() {
        return status;
    }
    public void setStatus(StatusPagamento status) {
        this.status = status;
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
                ", status=" + status +
                '}';
    }
}
