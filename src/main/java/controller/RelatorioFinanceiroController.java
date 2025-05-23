//package controller;
//
//
//import dao.RelatorioFinanceiroDAO;
//import model.RelatorioFinanceiro;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//public class RelatorioFinanceiroController {
//     private RelatorioFinanceiroDAO relatorioDAO;
//
//    public RelatorioFinanceiroController(Connection connection) {
//        this.relatorioDAO = new RelatorioFinanceiroDAO(connection);
//    }
//    public void gerarRelatorio(RelatorioFinanceiro relatorio) {
//        try {
//            relatorioDAO.salvarRelatorio(relatorio);
//            System.out.println("Relatório criado com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro ao criar relatório: " + e.getMessage());
//        }
//    }
//
//    public void listarRelatorios() {
//        try {
//            List<RelatorioFinanceiro> lista = relatorioDAO.listarRelatorios();
//            if (lista.isEmpty()) {
//                System.out.println("Nenhum relatório encontrado.");
//            } else {
//                for (RelatorioFinanceiro r : lista) {
//                    System.out.println(r);
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao listar relatórios: " + e.getMessage());
//        }
//    }
//
//    public RelatorioFinanceiro buscarRelatorioPorId(int id) {
//        try {
//            return relatorioDAO.buscarRelatorioPorId(id);
//        } catch (SQLException e) {
//            System.err.println("Erro ao buscar relatório: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public void deletarRelatorio(int id) {
//        try {
//            relatorioDAO.deletarRelatorio(id);
//            System.out.println("Relatório deletado com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro ao deletar relatório: " + e.getMessage());
//        }
//    }
//
//}
