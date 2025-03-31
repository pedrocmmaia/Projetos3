package dao;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsarioDAO {
    private Connection conexao;
    
    public UsarioDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    public void Inserir(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipo().name());

            stmt.executeUpdate();
            System.out.println("Usuario inserido com sucesso");
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }
        } 
        catch(SQLException e){
            e.printStackTrace();
            
        }
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
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

    public List<Usuario> buscarTodos() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
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
    
    public void atualiar(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ?, tipo_usuario = ? WHERE id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipo().name());
            stmt.setInt(6, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }


    }
}

