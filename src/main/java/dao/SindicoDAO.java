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
        String sql = """
                SELECT
                s.id AS sindico_id,
                s.usuario_id AS sindico_usuario_id,
                u.id AS usuario_id,
                u.nome AS usuario_nome,
                u.email AS usuario_email,
                u.telefone AS usuario_telefone,
                u.tipo_usuario AS usuario_tipo_usuario
                FROM sindico s
                INNER JOIN usuario u ON s.usuario_id = u.id
                """;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sindico sindico = new Sindico(rs.getInt("usuario_id"));
                sindico.setId(rs.getInt("sindico_id"));
                sindico.setNome(rs.getString("usuario_nome"));
                sindico.setEmail(rs.getString("usuario_email"));
                sindico.setTelefone(rs.getString("usuario_telefone"));

                String tipoUsuarioStr = rs.getString("usuario_tipo_usuario");
                if (tipoUsuarioStr != null) {
                    sindico.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuarioStr));
                }

                sindicos.add(sindico);
            }
        }
        return sindicos;
    }


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
