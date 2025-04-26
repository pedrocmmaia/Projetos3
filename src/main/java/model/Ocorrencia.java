package model;
import java.time.LocalDateTime;

public class Ocorrencia {
    private Integer id;
    private String descricao;
    private LocalDateTime dataCriacao;
    private EstadoOcorrencia estadoOcorrencia;
    protected Morador morador;  

    public enum EstadoOcorrencia{
        ABERTO,
        EM_ANDAMENTO,
        RESOLVIDO;

        public static EstadoOcorrencia fromString(String tipo){
            tipo = tipo.toUpperCase();
            if (tipo.contains("ABERTO")) return ABERTO;
            if (tipo.contains("EM_ANDAMENTO")) return EM_ANDAMENTO;
            if (tipo.contains("RESOLVIDO")) return RESOLVIDO;
            throw new IllegalArgumentException("Tipo de estado inválido: " + tipo);
        }
    }

    public Ocorrencia(){

    }

    public Ocorrencia(String descricao, LocalDateTime dataCriacao, EstadoOcorrencia estadoOcorrencia, Morador morador){
        this.descricao = descricao;
        this.estadoOcorrencia = estadoOcorrencia;
        this.dataCriacao = dataCriacao;
        this.morador = morador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public EstadoOcorrencia getEstadoOcorrencia() {
        return estadoOcorrencia;
    }

    public void setEstadoOcorrencia(EstadoOcorrencia estadoOcorrencia) {
        this.estadoOcorrencia = estadoOcorrencia;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    @Override
    public String toString(){
        return "Ocorrência{" +
        "Descrição=" + descricao +
        ", morador=" + morador + 
        ", data e hora=" + dataCriacao +
        ", estado=" + estadoOcorrencia +
        "}";
    }
}
