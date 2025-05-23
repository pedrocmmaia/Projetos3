package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelatorioFinanceiro {
    private int id;
    private LocalDateTime periodoInicio;
    private LocalDateTime periodoFim;
    private BigDecimal totalArrecadado;
    private BigDecimal inadimplencia;

    public RelatorioFinanceiro(){};

    public RelatorioFinanceiro(int id, LocalDateTime periodoInicio, LocalDateTime periodoFim,
                               BigDecimal totalArrecadado, BigDecimal inadimplencia) {
        this.id = id;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.totalArrecadado = totalArrecadado;
        this.inadimplencia = inadimplencia;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(LocalDateTime periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public LocalDateTime getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(LocalDateTime periodoFim) {
        this.periodoFim = periodoFim;
    }

    public BigDecimal getTotalArrecadado() {
        return totalArrecadado;
    }

    public void setTotalArrecadado(BigDecimal totalArrecadado) {
        this.totalArrecadado = totalArrecadado;
    }

    public BigDecimal getInadimplencia() {
        return inadimplencia;
    }

    public void setInadimplencia(BigDecimal inadimplencia) {
        this.inadimplencia = inadimplencia;
    }
            
    public String gerarRelatorio() {
        return "Relatório Financeiro\n" +
               "Período: " + periodoInicio + " até " + periodoFim + "\n" +
               "Total Arrecadado: R$ " + totalArrecadado + "\n" +
               "Inadimplência: " + inadimplencia + "%";
    }



}
