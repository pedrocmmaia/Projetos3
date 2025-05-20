package controller;

import dao.AreaComumDAO;
import model.AreaComum;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AreaComumController {
    private AreaComumDAO areaComumDAO;

    public AreaComumController(Connection connection) {
        this.areaComumDAO = new AreaComumDAO(connection);
    }

    public void cadastrarAreaComum(String nome, boolean disponibilidade) {
        AreaComum area = new AreaComum(nome, disponibilidade);
        try {
            areaComumDAO.cadastrarAreaComum(area);
            System.out.println("Área comum cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar área comum: " + e.getMessage());
        }
    }

    public void buscarAreaComumPorId(int id) {
        try {
            AreaComum area = areaComumDAO.buscarAreaComumPorId(id);
            if (area != null) {
                System.out.println("Área comum encontrada: " + area.toString());
            } else {
                System.out.println("Área comum não encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar área comum: " + e.getMessage());
        }
    }

    public void listarAreasComuns() {
        try {
            List<AreaComum> areas = areaComumDAO.listarAreasComuns();
            if (areas.isEmpty()) {
                System.out.println("Nenhuma área comum cadastrada.");
            } else {
                for (AreaComum area : areas) {
                    System.out.println("ID: " + area.getId()
                            + " | Nome: " + area.getNome()
                            + " | Disponível: " + (area.isDisponibilidade() ? "Sim" : "Não"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar áreas comuns: " + e.getMessage());
        }
    }

    public void atualizarAreaComum(int id, String nome, boolean disponibilidade) {
        AreaComum area = new AreaComum(id, nome, disponibilidade);
        try {
            areaComumDAO.atualizarAreaComum(area);
            System.out.println("Área comum atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar área comum: " + e.getMessage());
        }
    }

    public void excluirAreaComum(int id) {
        try {
            areaComumDAO.excluirAreaComum(id);
            System.out.println("Área comum excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir área comum: " + e.getMessage());
        }
    }
}
