package model;

import java.time.LocalDateTime;

public class Comunicado {
    private Integer id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataEnvio;

    public Comunicado(Integer id, String titulo, String conteudo, LocalDateTime dataEnvio) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
    }

    public Comunicado(String titulo, String conteudo, LocalDateTime dataEnvio) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public String toString() {
        return "Comunicado{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
