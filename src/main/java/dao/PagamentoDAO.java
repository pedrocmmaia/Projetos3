package dao;

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
        String sql = "INSERT INTO pagamento (tipo, valor, data_vencimento) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getTipo().name());
            stmt.setFloat(2, pagamento.getValor());
            stmt.setDate(3, Date.valueOf(pagamento.getDataVencimento()));
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

                p.setTipo(Pagamento.TipoPagamento.valueOf(rs.getString("tipo")));
                p.setValor(rs.getFloat("valor"));
                p.setDataVencimento(rs.getDate("data_vencimento").toLocalDate());
                lista.add(p);
            }
        }

        return lista;
    }

    public void atualizarPagamento(Pagamento pagamento) throws SQLException {
        String sql = "UPDATE pagamento SET tipo = ?, valor = ?, data_vencimento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getTipo().name());
            stmt.setFloat(2, pagamento.getValor());
            stmt.setDate(3, Date.valueOf(pagamento.getDataVencimento()));
            stmt.setInt(4, pagamento.getId());
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
                p.setTipo(Pagamento.TipoPagamento.valueOf(rs.getString("tipo")));
                p.setValor(rs.getFloat("valor"));
                p.setDataVencimento(rs.getDate("data_vencimento").toLocalDate());
                return p;
            }
        }
        return null;
    }

}
