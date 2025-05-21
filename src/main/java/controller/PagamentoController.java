package controller;

import dao.PagamentoDAO;
import model.Pagamento;

import java.sql.SQLException;
import java.util.List;

public class PagamentoController {
    private final PagamentoDAO dao;

    public PagamentoController(PagamentoDAO dao) {
        this.dao = dao;
    }

    public void registrarPagamento(Pagamento pagamento) {
        try {
            dao.inserir(pagamento);
            System.out.println("Pagamento registrado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao registrar pagamento: " + e.getMessage());
        }
    }

    public void listarPagamentos() {
        try {
            List<Pagamento> pagamentos = dao.listarTodos();
            pagamentos.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("Erro ao listar pagamentos: " + e.getMessage());
        }
    }

    public void atualizarStatusPagamento(int id, Pagamento.StatusPagamento novoStatus) {
        try {
            dao.atualizarStatus(id, novoStatus);
            System.out.println("Status do pagamento atualizado.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status: " + e.getMessage());
        }
    }

    public void excluirPagamento(int id) {
        try {
            dao.remover(id);
            System.out.println("Pagamento removido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao remover pagamento: " + e.getMessage());
        }
    }
}
