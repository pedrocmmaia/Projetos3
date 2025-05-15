package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.OcorrenciaDAO;
import model.Ocorrencia;
import model.Usuario;

public class OcorrenciaController {
    private Connection connection;
    private  OcorrenciaDAO ocorrenciaDAO;

    public OcorrenciaController(Connection connection) {
            this.ocorrenciaDAO = new OcorrenciaDAO(connection);
    }

    public Integer adicionarOcorrencia(Ocorrencia ocorrencia) {
        try {
            Integer idGerado = ocorrenciaDAO.registrarOcorrencia(ocorrencia);
            if(idGerado != null) {
                System.out.println("Ocorrência adicionada com sucesso! ID: " + idGerado);
                return idGerado;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar ocorrência: " + e.getMessage());
        }
        return null;
    }

    public void listarOcorrencias() {
        try {
            List<Ocorrencia> ocorrencias = ocorrenciaDAO.listarTodasOcorrencias();
            if (ocorrencias.isEmpty()){
                System.out.println("Nenhuma ocorrência encontrada!");
            } else {
                System.out.println("===== Lista de Ocorrências =====");
                for (Ocorrencia o : ocorrencias){
                    System.out.println(formatarOcorrencia(o));
                    System.out.println("----------------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar ocorrências: " + e.getMessage());
        }
    }

    public Ocorrencia buscarOcorrenciaPorId(int id) {
        try {
            Ocorrencia ocorrencia = ocorrenciaDAO.buscarOcorrenciaPorId(id);
            if (ocorrencia !=null){
                System.out.println("===== Ocorrência =====");
                System.out.println(formatarOcorrencia(ocorrencia));
                System.out.println("----------------------------------");
            }else {
                System.out.println("Ocorrência não encontrado");
            }
            return ocorrencia;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ocorrência por ID: " + e.getMessage());
            return null;
        }
    }

    public void atualizarOcorrencia(Ocorrencia ocorrencia) {
        try {
            ocorrenciaDAO.atualizarOcorrenciaDao(ocorrencia);
            System.out.println("Ocorrência atualizada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizarOcorrenciaDao ocorrência: " + e.getMessage());
        }
    }

    public void deletarOcorrencia(int id) {
        try {
            ocorrenciaDAO.deletarOcorrenciaDAO(id);
            System.out.println("Ocorrência removida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar ocorrência: " + e.getMessage());
        }
    }
    private String formatarOcorrencia(Ocorrencia o) {
        StringBuilder sb = new StringBuilder();

        sb.append("ID ocorrência: ").append(o.getId()).append("\n");
        sb.append("Data de criação: ").append(o.getDataCriacao()).append("\n");
        sb.append("Tipo de ocorrência: ").append(o.getTipoOcorrencia()).append("\n");
        sb.append("Status da ocorrência: ").append(o.getEstadoOcorrencia()).append("\n");
        sb.append("Descrção da ocorrência: ").append(o.getDescricao()).append("\n");
        sb.append("Morador: ").append(o.getMorador().getNome()).append("\n");

        if (o.getMorador().getApartamento() != null) {
            sb.append("Bloco: ").append(o.getMorador().getApartamento().getBlocoId()).append("\n");
            sb.append("Apartamento: ").append(o.getMorador().getApartamento().getNumero()).append("\n");
        }

        return sb.toString();
    }
}

