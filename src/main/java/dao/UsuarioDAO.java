package dao;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;
    
    public UsuarioDAO(Connection connection){
        this.connection = connection;
    }
    
    public void criarUsuario(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipo().name());

            stmt.executeUpdate();
            System.out.println("Usuario cadastrado com sucesso");
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }
        } catch(SQLException e){
            e.printStackTrace();
            
        }
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    Usuario.Tipo.valueOf(rs.getString("tipo_usuario"))
                );
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id"), 
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    Usuario.Tipo.valueOf(rs.getString("tipo_usuario"))
                    ));
            }
        }
        return usuarios;
    }
    
    public void atualiarUsuario(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ?, tipo_usuario = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipo().name());
            stmt.setInt(6, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarUsuario(int id) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

