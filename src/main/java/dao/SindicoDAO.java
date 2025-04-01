package dao;

import model.Sindico;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SindicoDAO {
    private Connection conexao;

    public SindicoDAO(Connection conexao){
        this.conexao = conexao;
    }

    public void inserir(Sindico sindico) throws SQLException{
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, sindico.getNome());
            stmt.setString(2, sindico.getEmail());
            stmt.setString(3, sindico.getSenha());
            stmt.setString(4, sindico.getTelefone());
            stmt.setString(5, Usuario.Tipo.Sindico.name());

            stmt.executeUpdate();
            System.out.println("Sindico inserido com sucesso");

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                sindico.setId(rs.getInt(1));
            }
        }
    }
    
    public Sindico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ? AND tipo_usuario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, Usuario.Tipo.Sindico.name());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Sindico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone")
                );
            }
        }
        return null;
    }

    public List<Sindico> buscarTodos() throws SQLException{
        List<Sindico> sindicos = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo_usuario = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, Usuario.Tipo.Sindico.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                sindicos.add(new Sindico(
                    rs.getInt("id"),
                     rs.getString("nome"),
                      rs.getString("email"),
                       rs.getString("senha"),
                        rs.getString("telefone")
                        ));
            }
        }
        return sindicos;
    }

    public void atualizar(Sindico sindico) throws SQLException{
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id = ? AND tipo_usuario = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, sindico.getNome());
            stmt.setString(2, sindico.getEmail());
            stmt.setString(3, sindico.getSenha());
            stmt.setString(4, sindico.getTelefone());
            stmt.setInt(5, sindico.getId());
            stmt.setString(6, Usuario.Tipo.Morador.name());

            stmt.executeUpdate();
            System.out.println("Sindico atualizado com sucesso");
        }
    }

    public void deletar(int id) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id = ? AND tipo_usuario = ?";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.setString(2, Usuario.Tipo.Sindico.name());
            stmt.executeUpdate();
            System.out.println("Sindico deletado com sucesso");
        }
    }

}
