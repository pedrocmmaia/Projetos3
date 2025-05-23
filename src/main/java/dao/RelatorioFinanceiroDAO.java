//package dao;
//
//import model.RelatorioFinanceiro;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RelatorioFinanceiroDAO {
//    private Connection connection;
//
//    public RelatorioFinanceiroDAO(Connection connection) {
//        this.connection = connection;
//    }
//// ajusta aqui que ta dando erro
//   public void salvarRelatorio(RelatorioFinanceiro relatorio) throws SQLException {
//        String sql = "INSERT INTO relatorio_financeiro (periodo_inicio, periodo_fim, total_arrecadado, inadimplencia) " +
//                     "VALUES (?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setTimestamp(1, Timestamp.valueOf(relatorio.getPeriodoInicio()));
//            stmt.setTimestamp(2, Timestamp.valueOf(relatorio.getPeriodoFim()));
////            stmt.setBigDecimal(3, BigDecimal.valueOf(relatorio.getTotalArrecadado()));
////            stmt.setBigDecimal(4, BigDecimal.valueOf(relatorio.getInadimplencia()));
//            stmt.executeUpdate();
//        }
//    }
//
//    public List<RelatorioFinanceiro> listarRelatorios() throws SQLException {
//        String sql = "SELECT * FROM relatorio_financeiro";
//        List<RelatorioFinanceiro> lista = new ArrayList<>();
//
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                RelatorioFinanceiro r = new RelatorioFinanceiro();
//                r.setId(rs.getInt("id"));
//                r.setPeriodoInicio(rs.getTimestamp("periodo_inicio").toLocalDateTime());
////                r.setPeriodoFim(rs.getTimestamp("periodo_fim").toLocalDateTime());
////                r.setTotalArrecadado(rs.getBigDecimal("total_arrecadado").floatValue());
////                r.setInadimplencia(rs.getBigDecimal("inadimplencia").floatValue());
//                lista.add(r);
//            }
//        }
//
//        return lista;
//    }
//
//    public RelatorioFinanceiro buscarRelatorioPorId(int id) throws SQLException {
//        String sql = "SELECT * FROM relatorio_financeiro WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    RelatorioFinanceiro r = new RelatorioFinanceiro();
//                    r.setId(rs.getInt("id"));
//                    r.setPeriodoInicio(rs.getTimestamp("periodo_inicio").toLocalDateTime());
//                    r.setPeriodoFim(rs.getTimestamp("periodo_fim").toLocalDateTime());
//                    r.setTotalArrecadado(rs.getBigDecimal("total_arrecadado").floatValue());
//                    r.setInadimplencia(rs.getBigDecimal("inadimplencia").floatValue());
//                    return r;
//                }
//            }
//        }
//        return null;
//    }
//
//    public void deletarRelatorio(int id) throws SQLException {
//        String sql = "DELETE FROM relatorio_financeiro WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        }
//    }
//}
//
