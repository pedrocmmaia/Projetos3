package dao;

import model.Sindico;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SindicoDAO {
    private Connection connection;

    public SindicoDAO(Connection connection){
        this.connection = connection;
    }

    public Integer cadastrarSindico(Sindico sindico) throws SQLException{
        String sql = "INSERT INTO sindico (usuario_id) VALUES (?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, sindico.getUsuarioId());

            stmt.executeUpdate();
            System.out.println("Sindico inserido com sucesso");

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                sindico.setId(rs.getInt(1));
            }
            return sindico.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Sindico buscarSindicoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM sindico WHERE id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int usuarioId = rs.getInt("usuario_id");
                Sindico sindico = new Sindico(usuarioId);
                sindico.setId(id);
                return sindico;
            }
        }
        return null;
    }

    public List<Sindico> listarSindicos() throws SQLException{
        List<Sindico> sindicos = new ArrayList<>();
        String sql = "SELECT * FROM sindico";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sindico sindico = new Sindico(rs.getInt("usuario_id"));
                sindico.setId(rs.getInt("id"));
                sindicos.add(sindico);
            }
        }
        return sindicos;
    }

//    public void atualizarOcorrenciaDao(Sindico sindico) throws SQLException{
//        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id = ? AND tipo_usuario = ?";
//
//        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
//            stmt.setString(1, sindico.getNome());
//            stmt.setString(2, sindico.getEmail());
//            stmt.setString(3, sindico.getSenha());
//            stmt.setString(4, sindico.getTelefone());
//            stmt.setInt(5, sindico.getId());
//            stmt.setString(6, Usuario.Tipo.Morador.name());
//
//            stmt.executeUpdate();
//            System.out.println("Sindico atualizado com sucesso");
//        }
//    }

    public void deletarSindico(int id) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id = ? ";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Sindico deletado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar sindico: " + e.getMessage());
        }
    }

}
