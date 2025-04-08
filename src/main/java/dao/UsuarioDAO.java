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
            System.out.println("Usuario cadastrado com sucesso");
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                int idGerado = rs.getInt(1);
                usuario.setId(idGerado);
                return idGerado;
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
                return new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    Usuario.TipoUsario.valueOf(rs.getString("tipo_usuario").toUpperCase())
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
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone"),
                    Usuario.TipoUsario.valueOf(rs.getString("tipo_usuario").toUpperCase())
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
            stmt.setString(5, usuario.getTipoUsario().toString());
            stmt.setInt(6, usuario.getId());
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

