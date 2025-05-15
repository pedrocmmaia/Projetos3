package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.sql.Connection;

public class AuthService {
    private UsuarioDAO usuarioDAO;

    public AuthService(Connection connection){
        this.usuarioDAO =  new UsuarioDAO(connection);
    }

    public Usuario login(String email, String senha){
        try{
            Usuario usuario = usuarioDAO.buscarPorEmail(email);
            if (usuario != null && usuario.getSenha().equals(senha)){
                System.out.println("Login  bem-sucedido! Bem-vindo, " + usuario.getNome());
                return usuario;
            } else {
                System.out.println("E-mail ou senha inválidos. ");
            }
        } catch (Exception e) {
            System.err.println("Erro ou fazer login: " + e.getMessage());
        }
        return  null;
    }
}
