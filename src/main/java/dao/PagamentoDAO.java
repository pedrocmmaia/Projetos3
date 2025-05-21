package dao;

import model.Pagamento;
import model.Morador;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    private final Connection connection;

    public PagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO pagamento_condominio (valor, data_pagamento, tipo_pagamento, status_pagamento, morador_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, pagamento.getValor());
            stmt.setTimestamp(2, Timestamp.valueOf(pagamento.getDataPagamento()));
            stmt.setString(3, pagamento.getTipoPagamento().name());
            stmt.setString(4, pagamento.getStatusPagamento().name());
            stmt.setInt(5, pagamento.getMorador().getId());
            stmt.executeUpdate();
        }
    }

    public List<Pagamento> listarTodos() throws SQLException {
        List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagamento_condominio";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setDataPagamento(rs.getTimestamp("data_pagamento").toLocalDateTime());
                pagamento.setTipoPagamento(Pagamento.TipoPagamento.valueOf(rs.getString("tipo_pagamento")));
                pagamento.setStatusPagamento(Pagamento.StatusPagamento.valueOf(rs.getString("status_pagamento")));

                Morador morador = new Morador();
                morador.setId(rs.getInt("morador_id"));
                pagamento.setMorador(morador);

                lista.add(pagamento);
            }
        }
        return lista;
    }

    public void atualizarStatus(int id, Pagamento.StatusPagamento novoStatus) throws SQLException {
        String sql = "UPDATE pagamento_condominio SET status_pagamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoStatus.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM pagamento_condominio WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
