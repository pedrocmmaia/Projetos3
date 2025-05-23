package dao;

import model.Apartamento;
import model.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    
   private Connection connection;

    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarPagamento(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO pagamento (apartamento_id, tipo, valor, data_vencimento, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getApartamento().getId());
            stmt.setString(2, pagamento.getTipo().name());
            stmt.setFloat(3, pagamento.getValor());
            stmt.setTimestamp(4, Timestamp.valueOf(pagamento.getDataVencimento()));
            stmt.setString(5, pagamento.getStatus().name());
            stmt.executeUpdate();
        }
    }

    public List<Pagamento> listarPagamentos() throws SQLException {
        List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagamento";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pagamento p = new Pagamento();
                p.setId(rs.getInt("id"));
                
                Apartamento a = new Apartamento();
                a.setId(rs.getInt("apartamento_id"));
                p.setApartamento(a);
                
                p.setTipo(Pagamento.TipoPagamento.valueOf(rs.getString("tipo")));
                p.setValor(rs.getFloat("valor"));
                p.setDataVencimento(rs.getTimestamp("data_vencimento").toLocalDateTime());
                p.setStatus(Pagamento.StatusPagamento.valueOf(rs.getString("status")));
                lista.add(p);
            }
        }

        return lista;
    }

    public void atualizarPagamento(Pagamento pagamento) throws SQLException {
        String sql = "UPDATE pagamento SET apartamento_id = ?, tipo = ?, valor = ?, data_vencimento = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pagamento.getApartamento().getId());
            stmt.setString(2, pagamento.getTipo().name());
            stmt.setFloat(3, pagamento.getValor());
            stmt.setTimestamp(4, Timestamp.valueOf(pagamento.getDataVencimento()));
            stmt.setString(5, pagamento.getStatus().name());
            stmt.setInt(6, pagamento.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarPagamento(int id) throws SQLException {
        String sql = "DELETE FROM pagamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Pagamento buscarPagamentoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pagamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pagamento p = new Pagamento();
                p.setId(rs.getInt("id"));

                Apartamento a = new Apartamento();
                a.setId(rs.getInt("apartamento_id"));
                p.setApartamento(a);

                p.setTipo(Pagamento.TipoPagamento.valueOf(rs.getString("tipo")));
                p.setValor(rs.getFloat("valor"));
                p.setDataVencimento(rs.getTimestamp("data_vencimento").toLocalDateTime());
                p.setStatus(Pagamento.StatusPagamento.valueOf(rs.getString("status")));
                return p;
            }
        }
        return null;
    }

}
