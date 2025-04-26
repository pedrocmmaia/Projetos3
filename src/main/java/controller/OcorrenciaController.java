package controller;

import java.sql.SQLException;
import java.util.List;

import dao.OcorrenciaDAO;
import model.Ocorrencia;

public class OcorrenciaController {
    private OcorrenciaDAO ocorrenciaDAO;

    public OcorrenciaController() {
        try {
            this.ocorrenciaDAO = new OcorrenciaDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao iniciar conexão com o banco: " + e.getMessage(), e);
        }
    }

    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        try {
            ocorrenciaDAO.inserir(ocorrencia);
            System.out.println("Ocorrência adicionada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar ocorrência: " + e.getMessage());
        }
    }

    public List<Ocorrencia> listarOcorrencias() {
        try {
            return ocorrenciaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar ocorrências: " + e.getMessage());
            return null;
        }
    }

    public Ocorrencia buscarOcorrenciaPorId(int id) {
        try {
            return ocorrenciaDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar ocorrência por ID: " + e.getMessage());
            return null;
        }
    }

    public void atualizarOcorrencia(Ocorrencia ocorrencia) {
        try {
            ocorrenciaDAO.atualizar(ocorrencia);
            System.out.println("Ocorrência atualizada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar ocorrência: " + e.getMessage());
        }
    }

    public void deletarOcorrencia(int id) {
        try {
            ocorrenciaDAO.deletar(id);
            System.out.println("Ocorrência removida com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar ocorrência: " + e.getMessage());
        }
    }
}
