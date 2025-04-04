package controller;

import dao.BlocoDAO;
import model.Bloco;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BlocoController {
    private BlocoDAO blocoDAO;

    public BlocoController(Connection connection) {
        this.blocoDAO = new BlocoDAO(connection);
    }

    public void cadastrarBloco(String nome) {
        Bloco bloco = new Bloco(nome);
        try {
            blocoDAO.cadastrarBloco(bloco);
            System.out.println("Bloco cadastrado com sucesso!");
        }catch (SQLException e){
            System.err.println("Erro ao cdastrar bloco: " + e.getMessage());
        }
    }

    public void buscarBlocoPorId(int id) {
        try {
            Bloco bloco = blocoDAO.buscarBlocoPorId(id);
            if (bloco != null){
                System.out.println("Bloco encontrado: " + bloco.toString());
            } else {
                System.out.println("Bloco não encontrado");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar bloco: " + e.getMessage());
        }
    }

    public void listarBlocos() {
        try {
            List<Bloco> blocos = blocoDAO.listarBlocos();
            if (blocos.isEmpty()){
                System.out.println("Nenhum bloco cadastrado.");
            } else {
                for(Bloco b : blocos){
                    System.out.println("ID: " + b.getId() + " | Nome: " + b.getNome());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao lsitar blocos: " + e.getMessage());
        }
    }

    // Atualizar bloco
    public void atualizarBloco(int id, String nome) {
        Bloco bloco = new Bloco(id, nome);
        try {
            blocoDAO.atualizarBloco(bloco);
            System.out.println("Bloco atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar bloco");
        }
    }

    public void excluirBloco(int id) {
        try{
            blocoDAO.excluirBloco(id);
            System.out.println(" Bloco excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao exclui bloco: " + e.getMessage());
        }

    }
}
