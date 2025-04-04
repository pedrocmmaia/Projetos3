package controller;

import dao.UsuarioDAO;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController(Connection connection) {
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    public void cadastrarUsuario(String nome, String email, String senha, String telefone, Usuario.Tipo tipo) {
        Usuario usuario = new Usuario(0, nome, email, senha, telefone, tipo);
        try {
            usuarioDAO.criarUsuario(usuario);
            System.out.println("Usuário cadastrado com sucesso! ");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }


    public void buscarUsuarioPorId(int id) {
        try {
            Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
            if (usuario != null) {
                System.out.println("Usuário encontrado: " + usuario.toString());
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }


    public void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
            } else {
                for (Usuario u : usuarios) {
                    System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public void atualizarUsuario(int id, String nome, String email, String senha, String telefone, Usuario.Tipo tipo) {
        Usuario usuario = new Usuario(id, nome, email, senha, telefone, tipo);
        try {
            usuarioDAO.atualiarUsuario(usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void deletarUsuario(int id) {
        try {
            usuarioDAO.deletarUsuario(id);
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
