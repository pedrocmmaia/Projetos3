package controller;

import dao.UsarioDAO;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private UsarioDAO usuarioDAO;

    public UsuarioController(Connection conexao) {
        this.usuarioDAO = new UsarioDAO(conexao);
    }

    public void adicionarUsuario(String nome, String email, String senha, String telefone, Usuario.Tipo tipo) {
        Usuario usuario = new Usuario(0, nome, email, senha, telefone, tipo);
        try {
            usuarioDAO.Inserir(usuario);
            System.out.println("Usuário cadastrado com sucesso! ID: " + usuario.getId());
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }


    public void buscarUsuarioPorId(int id) {
        try {
            Usuario usuario = usuarioDAO.buscarPorId(id);
            if (usuario != null) {
                System.out.println("Usuário encontrado: " + usuario.getNome());
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }


    public void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.buscarTodos();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
            } else {
                for (Usuario u : usuarios) {
                    System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
    }

    public void atualizarUsuario(int id, String nome, String email, String senha, String telefone, Usuario.Tipo tipo) {
        Usuario usuario = new Usuario(id, nome, email, senha, telefone, tipo);
        try {
            usuarioDAO.atualiar(usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void deletarUsuario(int id) {
        try {
            usuarioDAO.deletar(id);
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
