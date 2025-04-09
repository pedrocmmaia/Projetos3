package controller;

import dao.AdministradorDAO;
import model.Administrador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdministradorController {
    private AdministradorDAO administradorDAO;

    public AdministradorController(Connection connection){
        this.administradorDAO = new AdministradorDAO(connection);
    }

    public void cadastrarAdministrador(int usuario_id){
        Administrador administrador = new Administrador(usuario_id);
        try {
            Integer idGerado = administradorDAO.cadastrarAdministrador(administrador);
            if (idGerado != null) {
                System.out.println("Administrador cadastrado com sucesso ID: " +idGerado);
            } else {
                System.out.println("Erro ao cadastrar administrador");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }

    public void buscarAdministradorPorId(int id) {
        try {
            Administrador administrador = administradorDAO.buscarAdministradorPorId(id);
            if (administrador != null){
                System.out.println("Administrador encontrado: " + administrador.getNome());
            } else {
                System.out.println("Administrador não encontrado");
            }
        }catch (SQLException e) {
            System.err.println("Erro ao buscar administrador: " + e.getMessage());
        }
    }

    public void listarAdministradores() {
        try {
            List<Administrador> administradores = administradorDAO.listarAdministradores();
            if (administradores.isEmpty()){
                System.out.println("Nenhum administrador encontrado");
            }else {
                for (Administrador a : administradores){
                    System.out.println(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar administradores: " + e.getMessage());
        }
    }

    public void deletarAdministrador(int id) {
        try {
            administradorDAO.deletarAdministrador(id);
            System.out.println("Administrador deletado com sucesso!");
        }catch (SQLException e) {
            System.err.println("Erro ao deletar administrador: " + e.getMessage());
        }
    }
}
