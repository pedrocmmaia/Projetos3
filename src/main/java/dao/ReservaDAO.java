package dao;

import model.Reserva;
import model.StatusReserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas (morador_id, area_id, data_reserva, status) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getMoradorId());
            stmt.setInt(2, reserva.getAreaId());
            stmt.setDate(3, new java.sql.Date(reserva.getDataReserva().getTime()));
            stmt.setString(4, reserva.getStatus().name());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reserva.setId(rs.getInt("id"));
            }
        }
    }

    public Reserva buscarReservaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Reserva(
                        rs.getInt("id"),
                        rs.getInt("morador_id"),
                        rs.getInt("area_id"),
                        rs.getDate("data_reserva"),
                        StatusReserva.valueOf(rs.getString("status"))
                );
            }
        }
        return null;
    }

    public List<Reserva> listarReservas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("morador_id"),
                        rs.getInt("area_id"),
                        rs.getDate("data_reserva"),
                        StatusReserva.valueOf(rs.getString("status"))
                );
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public List<Reserva> listarReservasPorMorador(int moradorId) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE morador_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, moradorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("morador_id"),
                        rs.getInt("area_id"),
                        rs.getDate("data_reserva"),
                        StatusReserva.valueOf(rs.getString("status"))
                );
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public void atualizarReserva(Reserva reserva) throws SQLException {
        String sql = "UPDATE reservas SET morador_id = ?, area_id = ?, data_reserva = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getMoradorId());
            stmt.setInt(2, reserva.getAreaId());
            stmt.setDate(3, new java.sql.Date(reserva.getDataReserva().getTime()));
            stmt.setString(4, reserva.getStatus().name());
            stmt.setInt(5, reserva.getId());

            stmt.executeUpdate();
        }
    }

    public void excluirReserva(int id) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
