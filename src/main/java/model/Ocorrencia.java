package model;
import java.time.LocalDateTime;

public class Ocorrencia {
    private Integer id;
    private String descricao;
    private LocalDateTime dataCriacao;
    private TipoOcorrencia tipoOcorrencia;
    private EstadoOcorrencia estadoOcorrencia;
    protected Morador morador;  

    public enum EstadoOcorrencia{
        ABERTO,
        EM_ANDAMENTO,
        RESOLVIDO;

        public static EstadoOcorrencia fromString(String estado){
            estado = estado.toUpperCase();
            if (estado.contains("ABERTO")) return ABERTO;
            if (estado.contains("EM_ANDAMENTO")) return EM_ANDAMENTO;
            if (estado.contains("RESOLVIDO")) return RESOLVIDO;
            throw new IllegalArgumentException("Estado inválido: " + estado);
        }
    }

    public enum TipoOcorrencia{
        MANUTENÇÃO,
        RECLAMAÇÃO,
        OUTRO;

        public static  TipoOcorrencia fromString(String tipo){
            tipo = tipo.toUpperCase();
            if (tipo.contains("MANUTENÇÃO")) return MANUTENÇÃO;
            if (tipo.contains("RECLAMAÇÃO")) return RECLAMAÇÃO;
            if (tipo.contains("OUTRO")) return OUTRO;
            throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
    }

    public Ocorrencia(){

    }

    public Ocorrencia(String descricao, LocalDateTime dataCriacao, TipoOcorrencia tipoOcorrencia, EstadoOcorrencia estadoOcorrencia, int mooradorId){
        this.descricao = descricao;
        this.tipoOcorrencia = tipoOcorrencia;
        this.estadoOcorrencia = estadoOcorrencia;
        this.dataCriacao = dataCriacao;
        this.morador = new Morador();
        this.morador.setId(mooradorId);
    }

    public Ocorrencia(Integer id, String descricao, LocalDateTime dataCriacao, TipoOcorrencia tipoOcorrencia, EstadoOcorrencia estadoOcorrencia, Morador morador) {
        this.id = id;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.tipoOcorrencia = tipoOcorrencia;
        this.estadoOcorrencia = estadoOcorrencia;
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

    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
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
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", tipoOcorrencia=" + tipoOcorrencia +
                ", estadoOcorrencia=" + estadoOcorrencia +
                ", morador=" + morador +
                '}';
    }
}
