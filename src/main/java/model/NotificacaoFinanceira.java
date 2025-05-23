package model;

import java.time.LocalDateTime;

public class NotificacaoFinanceira {
    private int id;
    private Morador moradorResponsavel;
    private String mensagem;
    private LocalDateTime dataEnvio;

    public NotificacaoFinanceira(int id, Morador moradorResponsavel, String mensagem, LocalDateTime dataEnvio) {
        this.id = id;
        this.moradorResponsavel = moradorResponsavel;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }

    public NotificacaoFinanceira() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Morador getMoradorResponsavel() {
        return moradorResponsavel;
    }

    public void setMoradorResponsavel(Morador moradorResponsavel) {
        this.moradorResponsavel = moradorResponsavel;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public void enviarNotificacao() {
        System.out.println("Enviando notificação para " + moradorResponsavel.getNome() + ": " + mensagem);
        this.dataEnvio = LocalDateTime.now();
    }
}
