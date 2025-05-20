package controller;

import dao.SindicoDAO;
import model.Sindico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SindicoController {
    private SindicoDAO sindicoDAO;

    public SindicoController(Connection connection){
        this.sindicoDAO = new SindicoDAO(connection);
    }

    public void cadastraSindico(int usario_id){
        Sindico sindico = new Sindico(usario_id);
        try{
            Integer idGerado = sindicoDAO.cadastrarSindico(sindico);
            if (idGerado != null){
                System.out.println("Sindico cadastrado com sucesso ID: " + idGerado);
            }else {
                System.out.println("Erro ao cadastrar morador");
            }
        } catch (SQLException e){
            System.err.println("Erro ao cadastrar sindico: "+e.getMessage());
        }
    }

    public void buscarSindicoPorId(int id){
        try{
            Sindico sindico = sindicoDAO.buscarSindicoPorId(id);
            if (sindico != null) {
                System.out.println("Sindico encontrado: "+sindico.getNome());
                System.out.println(formatarSindico(sindico));
                System.out.println("----------------------------------");
            }
            else{
                System.out.println("Sindico nao encontrado");
            }
        }catch (SQLException e) {
            System.err.println("Erro ao buscar sindico: "+e.getMessage());
        }
    }

    public void listarSindicos(){
        try {
            List<Sindico> sindicos = sindicoDAO.listarSindicos();
            if(sindicos.isEmpty()){
                System.out.println("Nenhum sindico cadastrado");
            }
            else{
                System.out.println("===== Lista de Síndicos =====");
                for(Sindico s : sindicos){
                    System.out.println(formatarSindico(s));
                    System.out.println("----------------------------------");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar sindicos: "+e.getMessage());
        }
    }

    private String formatarSindico(Sindico s) {
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(s.getNome()).append("\n");
        sb.append("Email: ").append(s.getEmail()).append("\n");
        sb.append("Telefone: ").append(s.getTelefone()).append("\n");

        return sb.toString();
    }

//    public void atualizarSindico(int id, String nome, String email, String senha, String telefone){
//        Sindico sindico = new Sindico(id, nome,email ,senha, telefone);
//        try {
//            sindicoDAO.atualizarOcorrenciaDao(sindico);
//            System.out.println("Sindico atualizado com sucesso");
//
//        } catch (SQLException e) {
//            System.err.println("Erro ao encontrar sindico "+ e.getMessage());
//        }
//    }

    public void deletarSindico(int id){
        try{
            sindicoDAO.deletarSindico(id);
            System.out.println("Sindico deletado com sucesso! ");
        }catch(SQLException e){
            System.err.println("Erro ao deletar sindico: "+ e.getMessage());
        }
    }
}
