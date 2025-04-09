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
    
    public Integer criarUsuario(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipoUsario().toString());

            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                String nomeUsuario = rs.getString("nome");
                String emailUsuario = rs.getString("email");
                String senhaUsuario = rs.getString("senha");
                String telefoneUsuario = rs.getString("telefone");
                String tipoUsuarioStr =  rs.getString("tipo_usuario");

                Usuario.TipoUsuario tipoUsuario = Usuario.TipoUsuario.valueOf(tipoUsuarioStr.toUpperCase());

                Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario,telefoneUsuario, tipoUsuario);
                usuario.setId(rs.getInt("id"));
                return usuario;
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
                Usuario.TipoUsuario tipo = Usuario.TipoUsuario.fromString(rs.getString("tipo_usuario"));
                usuarios.add(new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    Usuario.TipoUsuario.fromString(rs.getString("tipo_usuario"))
                    ));
            }
        }
        return usuarios;
    }
    
    public void atualizarUsuario(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setInt(5, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void deletarUsuario(int id) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuário deletado com sucesso!");
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }
}

