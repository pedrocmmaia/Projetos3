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
                System.out.println("Síndico cadastrado com sucesso ID: " + idGerado);
            }else {
                System.out.println("Erro ao cadastrar síndico");
            }
        } catch (SQLException e){
            System.err.println("Erro ao cadastrar síndico: "+e.getMessage());
        }
    }

    public void buscarSindicoPorId(int id){
        try{
            Sindico sindico = sindicoDAO.buscarSindicoPorId(id);
            if (sindico != null) {
                System.out.println("Síndico encontrado: "+sindico.getNome());
            }
            else{
                System.out.println("Síndico nao encontrado");
            }
            
            }catch (SQLException e) {
                System.err.println("Erro ao buscar síndico: "+e.getMessage());
        }
    }

    public void listarSindicos(){
        try {
            List<Sindico> sindicos = sindicoDAO.listarSindicos();
            if(sindicos.isEmpty()){
                System.out.println("Nenhum síndico cadastrado");
            }
            else{
                for(Sindico s : sindicos){
                    System.out.println(formatarSindico(s));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar síndicos: "+e.getMessage());
        }
    }

    public void deletarSindico(int id){
        try{
            sindicoDAO.deletarSindico(id);
            System.out.println("Síndico deletado com sucesso! ");
        }catch(SQLException e){
            System.err.println("Erro ao deletar síndico: "+ e.getMessage());

        }
    }

    private String formatarSindico(Sindico s){
        StringBuilder sb = new StringBuilder();

        sb.append("Nome: ").append(s.getNome()).append("\n");
        sb.append("Email: ").append(s.getEmail()).append("\n");
        sb.append("Telefone: ").append(s.getTelefone()).append("\n");

        return sb.toString();
    }
    
}
