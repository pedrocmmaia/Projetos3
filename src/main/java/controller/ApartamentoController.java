package controller;

import dao.ApartamentoDAO;
import model.Apartamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ApartamentoController {
    private ApartamentoDAO apartamentoDAO;

    public ApartamentoController(Connection connection) {
        this.apartamentoDAO = new ApartamentoDAO(connection);
    }

    public void cadastrarApartamento(int numero, int andar, int blocoId)  {
        Apartamento apartamento = new Apartamento(0, numero, andar, blocoId);
        try {
            apartamentoDAO.cadastrarApartamento(apartamento);
        }catch (SQLException e) {
            System.err.println("Erro ao cadastrar apartamento");
        }
    }

    public void buscarApartamentoPorId(int id){
        try {
            Apartamento apartamento = apartamentoDAO.buscarPorId(id);
            if(apartamento != null) {
                System.out.println("Apartamento encontrado" + apartamento.toString());
            } else {
                System.out.println("Apartamento encontrado: ");
            }
        }catch (SQLException e) {
            System.err.println("Erro ao busucar apartamento: " + e.getMessage());
        }
    }

    public void listarApartamentos() {
        try{
            List<Apartamento> apartamentos = apartamentoDAO.listarApartamentos();
            if (apartamentos.isEmpty()){
                System.out.println("Nenhum apartamento cadastrado");
            }else {
                for (Apartamento ap : apartamentos){
                    System.out.println("Bloco: " + ap.getBlocoId() +
                            " | Andar: " + ap.getAndar() +
                            " | Número: " + ap.getNumero());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar apartamentos: " + e.getMessage());
        }
    }

    public void atualizarApartamento(int id, int numero, int andar, int blocoId) {
        Apartamento apartamento = new Apartamento(id, numero, andar, blocoId);
        try {
            apartamentoDAO.atualizarApartamento(apartamento);
            System.out.println("Apartamento atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar apartamento: " + e.getMessage());
        }
    }

    public void excluirApartamento(int id) {
        try {
            apartamentoDAO.excluirApartamento(id);
            System.out.println("Apartamento excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir apartamento: " + e.getMessage());
        }
    }
}
