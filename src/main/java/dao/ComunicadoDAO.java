package dao;

import model.Comunicado;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComunicadoDAO {

    private Connection connection;

    public ComunicadoDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarComunicado(Comunicado comunicado) throws SQLException {
        String sql = "INSERT INTO comunicados (titulo, conteudo, data_envio) VALUES (?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comunicado.getTitulo());
            stmt.setString(2, comunicado.getConteudo());
            stmt.setTimestamp(3, Timestamp.valueOf(comunicado.getDataEnvio()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comunicado.setId(rs.getInt("id"));
                    System.out.println("Comunicado cadastrado com sucesso com ID: " + comunicado.getId());
                } else {
                    System.err.println("Erro ao obter o ID do comunicado cadastrado");
                }
            }
        }
    }

    public Comunicado buscarComunicadoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM comunicados WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Comunicado(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("conteudo"),
                        rs.getTimestamp("data_envio").toLocalDateTime()
                );
            }
        }
        return null;
    }

    public List<Comunicado> listarComunicados() throws SQLException {
        List<Comunicado> comunicados = new ArrayList<>();
        String sql = "SELECT * FROM comunicados ORDER BY data_envio DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comunicados.add(new Comunicado(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("conteudo"),
                        rs.getTimestamp("data_envio").toLocalDateTime()
                ));
            }
        }
        return comunicados;
    }

    public void atualizarComunicado(Comunicado comunicado) throws SQLException {
        String sql = "UPDATE comunicados SET titulo = ?, conteudo = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comunicado.getTitulo());
            stmt.setString(2, comunicado.getConteudo());
            stmt.setInt(3, comunicado.getId());

            stmt.executeUpdate();
        }
    }

    public void excluirComunicado(int id) throws SQLException {
        String sql = "DELETE FROM comunicados WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean existeComunicadoComId(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM comunicados WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
}
