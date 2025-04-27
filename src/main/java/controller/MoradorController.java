package controller;

import dao.MoradorDAO;
import model.Morador;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MoradorController{
    private MoradorDAO moradorDAO;

    public MoradorController(Connection conexao){
        this.moradorDAO = new MoradorDAO(conexao);
    }

    public Integer cadastrarMorador(int usuario_id, int apartamento_id){
        Morador morador = new Morador(usuario_id, apartamento_id);
        try {
            Integer idGerado = moradorDAO.cadastrarMorador(morador);
            if (idGerado != null) {
                System.out.println("Morador cadastrado com sucesso! ID: " + idGerado);
                return idGerado;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar morador: " + e.getMessage());
        }
        return null;
    }


    public void buscarMoradorPorId(int id) {
        try {
            Morador morador = moradorDAO.buscarDadosMoradorPorId(id);
            if (morador != null) {
                System.out.println("Morador encontrado:");
                System.out.println("Usuário ID: " + morador.getUsuarioId());
                System.out.println("Apartamento ID: " + morador.getApartamentoId());
            } else {
                System.out.println("Morador não encontrado");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar morador: " + e.getMessage());
        }
    }

    public void listarMoradores(){
        try {
            List<Morador> moradores = moradorDAO.listarMoradores();
            if(moradores.isEmpty()){
                System.out.println("Nenhum morador encontrado");
            }
            else{
                for(Morador m : moradores){
                    System.out.println(m);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar morador: "+ e.getMessage());
        }
    }

//    public void atualizarMorador(int id, String nome, String email, String senha, String telefone){
//        Morador morador = new Morador(id, nome, email, senha, telefone);
//        try {
//            moradorDAO.atualizarOcorrenciaDao(morador);
//            System.out.println("Morador atualizado com sucesso");
//
//        } catch (SQLException e) {
//            System.err.println("Erro ao atualizarOcorrenciaDao morador "+ e.getMessage());
//        }
//    }

    public void deletarMorador(int id){
        try {
            moradorDAO.deletarMorador(id);
            System.out.println("Morador deletado com sucesso! ");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar morador: "+e.getMessage());
        }
    }

public Morador obterMoradorPorId(int id) {
    try {
        return moradorDAO.buscarDadosMoradorPorId(id);
    } catch (SQLException e) {
        System.err.println("Erro ao buscar morador: " + e.getMessage());
        return null;
    }
}
}
