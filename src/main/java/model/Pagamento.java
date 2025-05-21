package model;

import java.time.LocalDateTime;

public class Pagamento {
    private Integer id;
    private Double valor;
    private LocalDateTime dataPagamento;
    private StatusPagamento statusPagamento;
    private TipoPagamento tipoPagamento;
    protected Morador morador;

    public enum StatusPagamento {
        PENDENTE,
        PAGO,
        ATRASADO;

        public static StatusPagamento fromString(String status) {
            status = status.toUpperCase();
            if (status.contains("PENDENTE")) return PENDENTE;
            if (status.contains("PAGO")) return PAGO;
            if (status.contains("ATRASADO")) return ATRASADO;
            throw new IllegalArgumentException("Status inválido: " + status);
        }
    }

    public enum TipoPagamento {
        BOLETO,
        CARTAO,
        PIX,
        TRANSFERENCIA;

        public static TipoPagamento fromString(String tipo) {
            tipo = tipo.toUpperCase();
            if (tipo.contains("BOLETO")) return BOLETO;
            if (tipo.contains("CARTAO")) return CARTAO;
            if (tipo.contains("PIX")) return PIX;
            if (tipo.contains("TRANSFERENCIA")) return TRANSFERENCIA;
            throw new IllegalArgumentException("Tipo de pagamento inválido: " + tipo);
        }
    }

    public Pagamento() {
    }

    public Pagamento(Double valor, LocalDateTime dataPagamento, TipoPagamento tipoPagamento, StatusPagamento statusPagamento, int moradorId) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
        this.morador = new Morador();
        this.morador.setId(moradorId);
    }

    public Pagamento(Integer id, Double valor, LocalDateTime dataPagamento, TipoPagamento tipoPagamento, StatusPagamento statusPagamento, Morador morador) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.tipoPagamento = tipoPagamento;
        this.statusPagamento = statusPagamento;
        this.morador = morador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    @Override
    public String toString() {
        return "PagamentoCondominio{" +
                "id=" + id +
                ", valor=" + valor +
                ", dataPagamento=" + dataPagamento +
                ", tipoPagamento=" + tipoPagamento +
                ", statusPagamento=" + statusPagamento +
                ", morador=" + morador +
                '}';
    }
}
