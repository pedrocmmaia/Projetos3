package controller;

import dao.MoradorDAO;
import model.Morador;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MoradorController{
    private MoradorDAO moradorDAO;

    public MoradorController(Connection conexao){
        this.moradorDAO = new MoradorDAO(conexao);
    }

    public void cadastrarMorador(String nome, String email, String senha, String telefone, Usuario.Tipo tipo){
        Morador morador = new Morador(0, nome, email, senha, telefone);
        try{
            moradorDAO.inserir(morador);
            System.out.println("Morador cadastrado com sucesso! ID: " + morador.getId());
        }catch (SQLException e){
            System.err.println("Erro ao cadastrar morador " + e.getMessage());
        }
    }

    public void buscarMoradorPorId(int id){
        try{
            Morador morador = moradorDAO.buscarPorId(id);
            if (morador != null){
                System.out.println("Morador encontrado: " + morador.getNome());
            }
            else{
                System.out.println("Morador não encontrado");
            }

        }catch(SQLException e){
            System.err.println("Erro ao buscar morador "+ e.getMessage());
        }
    }

    public void listarMorador(){
        try {
            List<Morador> moradores = moradorDAO.buscarTodos();
            if(moradores.isEmpty()){
                System.out.println("Nenhum morador encontrado");
            }
            else{
                for(Morador m : moradores){
                    System.out.println("ID: "+ m.getId() + " | Nome: "+ m.getNome());
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar morador: "+ e.getMessage());
        }
    }

    public void atualizarMorador(int id, String nome, String email, String senha, String telefone){
        Morador morador = new Morador(id, nome, email, senha, telefone);
        try {
            moradorDAO.atualizar(morador);
            System.out.println("Morador atualizado com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar morador "+ e.getMessage());
        }
    }

    public void deletarMorador(int id){
        try {
            moradorDAO.deletar(id);
            System.out.println("Morador deletado com sucesso! ");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar morador: "+e.getMessage());
        }
    }
}