package controller;

import dao.SindicoDAO;
import model.Sindico;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SindicoController {
    private SindicoDAO sindicoDAO;

    public SindicoController(Connection conexao){
        this.sindicoDAO = new SindicoDAO(conexao);
    }

    public void adicionarSindico(String nome, String email, String senha, String telefone, Usuario.Tipo tipo){
        Sindico sindico = new Sindico(0, nome, email, senha, telefone);
        try{
            sindicoDAO.inserir(sindico);
            System.out.println("Sindico cadastrado com sucesso ID: "+sindico.getId());
        } catch (SQLException e){
            System.err.println("Erro ao cadastrar sindico: "+e.getMessage());
        }
    }

    public void buscarSindicoPorId(int id){
        try{
            Sindico sindico = sindicoDAO.buscarPorId(id);
            if (sindico != null) {
                System.out.println("Sindico encontrado: "+sindico.getNome());
            }
            else{
                System.out.println("Sindico nao encontrado");
            }
            
            }catch (SQLException e) {
                System.err.println("Erro ao buscar sindico: "+e.getMessage());
        }
    }

    public void listarSindico(){
        try {
            List<Sindico> sindicos = sindicoDAO.buscarTodos();
            if(sindicos.isEmpty()){
                System.out.println("Nenhum sindico cadastrado");
            }
            else{
                for(Sindico s : sindicos){
                    System.out.println("ID: " + s.getId() + " | Nome: " + s.getNome());
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar sindicos: "+e.getMessage());
        }
    }

    public void atualizarSindico(int id, String nome, String email, String senha, String telefone){
        Sindico sindico = new Sindico(id, nome,email ,senha, telefone);
        try {
            sindicoDAO.atualizar(sindico);
            System.out.println("Sindico atualizado com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao encontrar sindico "+ e.getMessage());
        }
    }

    public void deletarSindico(int id){
        try{
            sindicoDAO.deletar(id);
            System.out.println("Sindico deletado com sucesso! ");
        }catch(SQLException e){
            System.err.println("Erro ao deletar sindico: "+ e.getMessage());;

        }
    }
    
}
