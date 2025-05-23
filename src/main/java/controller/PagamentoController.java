package controller;

import dao.PagamentoDAO;
import model.Pagamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PagamentoController {
    private PagamentoDAO pagamentoDAO;

    public PagamentoController(Connection connection) {
        this.pagamentoDAO = new PagamentoDAO(connection);
    } 
    
    public void cadastrarPagamento(Pagamento pagamento) {
        try {
            pagamentoDAO.registrarPagamento(pagamento);
            System.out.println("Pagamento cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar pagamento: " + e.getMessage());
        }
    }

    public void listarPagamentos() {
        try {
            List<Pagamento> lista = pagamentoDAO.listarPagamentos();
            if (lista.isEmpty()) {
                System.out.println("Nenhum pagamento encontrado.");
            } else {
                for (Pagamento p : lista) {
                    System.out.println(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pagamentos: " + e.getMessage());
        }
    }


    public void atualizarPagamento(Pagamento pagamento) {
        try {
            pagamentoDAO.atualizarPagamento(pagamento);
            System.out.println("Pagamento atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pagamento: " + e.getMessage());
        }
    }
     public void deletarPagamento(int id) {
        try {
            pagamentoDAO.deletarPagamento(id);
            System.out.println("Pagamento deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pagamento: " + e.getMessage());
        }
    }

    public Pagamento buscarPagamentoPorId(int id) {
        try {
            return pagamentoDAO.buscarPagamentoPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pagamento: " + e.getMessage());
            return null;
        }
    }
}
