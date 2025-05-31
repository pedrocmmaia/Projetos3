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

    public Integer cadastrarUsuario(Usuario usuario) {
        try {
            Integer idGerado = usuarioDAO.criarUsuario(usuario);
            if (idGerado != null){
                System.out.println("Usuário cadastrado com sucesso! ID: " + idGerado);
                return idGerado;
            } else {
                System.err.println("Erro ao cadastrar usuário: Falha ao obter o ID gerado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
        return null;
    }


    public void buscarUsuarioPorId(int id) {
        try {
            Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);

            if (usuario != null){
                System.out.println("Usuário encontrado: ");
                System.out.println("Usuário ID: " + usuario.getId());
                System.out.println("Usuário nome: " + usuario.getNome());
                System.out.println("Usuário email: " + usuario.getEmail());
                System.out.println("Usuário telefone: " + usuario.getTelefone());
                System.out.println("Usuário tipo: " + usuario.getTipoUsario());
            }else {
                System.out.println("Usuário não encontrado");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário com ID: " + e.getMessage());
        }
    }

    public Usuario obterUsuarioPorId(int id) {
        try {
            return usuarioDAO.buscarUsuarioPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }



    public void listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
            } else {
                for (Usuario u : usuarios) {
                    formartarUsuario(u);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public void atualizarUsuario(int id, String nome, String email, String senha, String telefone, Usuario.TipoUsuario tipo) {
        Usuario usuario = new Usuario(nome, email, senha, telefone, tipo);
        usuario.setId(id);
        try {
            usuarioDAO.atualizarUsuario(usuario);
            System.out.println("Usuário  atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizarOcorrenciaDao usuário " + e.getMessage());
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

    private String formartarUsuario(Usuario u){
        StringBuilder sb = new StringBuilder();

        sb.append("ID Usuário: ").append(u.getId()).append("\n");
        sb.append("Nome: ").append(u.getNome()).append("\n");
        sb.append("Email: ").append(u.getEmail()).append("\n");
        sb.append("Telefone: ").append(u.getTelefone()).append("\n");
        sb.append("Tipo: ").append(u.getTipoUsario()).append("\n");

        return sb.toString();
    }
}
