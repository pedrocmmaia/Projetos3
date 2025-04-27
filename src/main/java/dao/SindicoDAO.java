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
            stmt.setInt(1, sindico.getId());

            stmt.executeUpdate();
            System.out.println("Sindico inserido com sucesso");

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                sindico.setId(rs.getInt(1));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Sindico buscarSindicoPorId(int id) throws SQLException {
        String sql = """
                SELECT
                    s.id AS sindico_id,
                    u.id AS usuario_id,
                    u.nome AS usuario_nome,
                    u.email AS usuario_email,
                    u.telefone AS usuario_telefone,
                    u.tipo_usuario AS usuario_tipo
                FROM sindico s
                JOIN usuario u ON s.usuario_id = u.id
                WHERE s.id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Sindico sindico = new Sindico(
                        rs.getInt("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_email"),
                        null, //Preservar a senha
                        rs.getString("usuario_telefone"),
                        Usuario.TipoUsuario.valueOf(rs.getString("usuario_tipo"))
                );
                sindico.setId(rs.getInt("sindico_id"));
                return sindico;
            }
        }
        return null;
    }

    public List<Sindico> listarSindicos() throws SQLException{
        List<Sindico> sindicos = new ArrayList<>();
        String sql = """
            SELECT
                s.id AS sindico_id,
                u.id AS usuario_id,
                u.nome AS usuario_nome,
                u.email AS usuario_email,
                u.telefone AS usuario_telefone,
                u.tipo_usuario AS usuario_tipo
            FROM sindico s
            JOIN usuario u ON s.usuario_id = u.id
            """;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                    Sindico sindico = new Sindico(
                        rs.getInt("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_email"),
                        null,
                        rs.getString("usuario_telefone"),
                        Usuario.TipoUsuario.fromString(rs.getString("usuario_tipo"))
                );

                sindico.setId(rs.getInt("sindico_id"));
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
