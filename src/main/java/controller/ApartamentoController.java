package controller;

import dao.ApartamentoDAO;
import model.Apartamento;

import java.util.List;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController() {
        this.apartamentoDAO = new ApartamentoDAO();
    }

    // Cadastrar um novo apartamento
    public void cadastrarApartamento(int numero, int andar, int blocoId) {
        Apartamento apartamento = new Apartamento(0, numero, andar, blocoId); // ID inicializado como 0 (ou pode ser tratado no DAO)
        apartamentoDAO.cadastrarApartamento(apartamento);
        System.out.println("✅ Apartamento cadastrado com sucesso!");
    }

    // Listar todos os apartamentos
    public List<Apartamento> listarApartamentos() {
        return apartamentoDAO.listarApartamentos();
    }

    // Atualizar apartamento
    public void atualizarApartamento(int id, int numero, int andar, int blocoId) {
        Apartamento apartamento = new Apartamento(id, numero, andar, blocoId);
        apartamentoDAO.atualizarApartamento(apartamento);
        System.out.println("✅ Apartamento atualizado com sucesso!");
    }

    // Excluir apartamento pelo ID
    public void excluirApartamento(int id) {
        apartamentoDAO.excluirApartamento(id);
        System.out.println("✅ Apartamento excluído com sucesso!");
    }
}
