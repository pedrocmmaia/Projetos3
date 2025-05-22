package controller;

import dao.ComunicadoDAO;
import model.Comunicado;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ComunicadoController {

    private ComunicadoDAO comunicadoDAO;

    public ComunicadoController(Connection connection) {
        this.comunicadoDAO = new ComunicadoDAO(connection);
    }

    public void cadastrarComunicado(String titulo, String conteudo) {
        Comunicado comunicado = new Comunicado(titulo, conteudo, LocalDateTime.now());
        try {
            comunicadoDAO.cadastrarComunicado(comunicado);
            System.out.println("Comunicado cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar comunicado: " + e.getMessage());
        }
    }

    public void listarComunicados() {
        try {
            List<Comunicado> comunicados = comunicadoDAO.listarComunicados();
            if (comunicados.isEmpty()) {
                System.out.println("Nenhum comunicado encontrado.");
            } else {
                for (Comunicado c : comunicados) {
                    System.out.println(c.toString());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar comunicados: " + e.getMessage());
        }
    }

    public Comunicado buscarComunicadoPorId(int id) {
        try {
            Comunicado comunicado = comunicadoDAO.buscarComunicadoPorId(id);
            if (comunicado != null) {
                System.out.println(comunicado.toString());
            } else {
                System.out.println("Comunicado não encontrado.");
            }
            return comunicado;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar comunicado: " + e.getMessage());
            return null;
        }
    }

    public void atualizarComunicado(int id, String novoTitulo, String novoConteudo) {
        try {
            if (comunicadoDAO.existeComunicadoComId(id)) {
                Comunicado comunicado = new Comunicado(id, novoTitulo, novoConteudo, LocalDateTime.now());
                comunicadoDAO.atualizarComunicado(comunicado);
                System.out.println("Comunicado atualizado com sucesso!");
            } else {
                System.out.println("Comunicado com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar comunicado: " + e.getMessage());
        }
    }

    public void excluirComunicado(int id) {
        try {
            if (comunicadoDAO.existeComunicadoComId(id)) {
                comunicadoDAO.excluirComunicado(id);
                System.out.println("Comunicado excluído com sucesso!");
            } else {
                System.out.println("Comunicado com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir comunicado: " + e.getMessage());
        }
    }
}
