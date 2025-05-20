package dao;

import model.AreaComum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaComumDAO {

    private Connection connection;

    public AreaComumDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarAreaComum(AreaComum area) throws SQLException {
        String sql = "INSERT INTO area_comum (nome, disponibilidade) VALUES (?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, area.getNome());
            stmt.setBoolean(2, area.isDisponibilidade());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    area.setId(rs.getInt("id"));
                    System.out.println("Área comum cadastrada com sucesso com ID: " + area.getId());
                } else {
                    System.err.println("Erro ao obter o ID da área comum cadastrada");
                }
            }
        }
    }

    public AreaComum buscarAreaComumPorId(int id) throws SQLException {
        String sql = "SELECT * FROM area_comum WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AreaComum(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getBoolean("disponibilidade")
                );
            }
        }
        return null;
    }

    public List<AreaComum> listarAreasComuns() throws SQLException {
        List<AreaComum> areas = new ArrayList<>();
        String sql = "SELECT * FROM area_comum";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                areas.add(new AreaComum(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getBoolean("disponibilidade")
                ));
            }
        }
        return areas;
    }

    public void atualizarAreaComum(AreaComum area) throws SQLException {
        String sql = "UPDATE area_comum SET nome = ?, disponibilidade = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, area.getNome());
            stmt.setBoolean(2, area.isDisponibilidade());
            stmt.setInt(3, area.getId());

            stmt.executeUpdate();
        }
    }

    public void excluirAreaComum(int id) throws SQLException {
        String sql = "DELETE FROM area_comum WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean existeAreaComumComId(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM area_comum WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
