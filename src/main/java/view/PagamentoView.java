package view;

import config.DatabaseConfig;
import controller.PagamentoController;
import dao.PagamentoDAO;
import model.Pagamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PagamentoView {

    public static void menuPagamento() {
        try {
            System.out.println("🔗 Iniciando conexão com o banco de dados...");
            DatabaseConfig.criarTabelas();
            Connection connection = DatabaseConfig.getConnection();
            Scanner scanner = new Scanner(System.in);

            PagamentoDAO pagamentoDAO = new PagamentoDAO(connection);
            PagamentoController pagamentoController = new PagamentoController(pagamentoDAO);

            int opcao;
            do {
                System.out.println("\n--- MENU PAGAMENTO DE CONDOMÍNIO ---");
                System.out.println("1. Registrar pagamento");
                System.out.println("2. Listar pagamentos");
                System.out.println("3. Atualizar status do pagamento");
                System.out.println("4. Excluir pagamento");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        scanner.nextLine();
                        System.out.print("ID do morador: ");
                        int moradorId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Valor do pagamento: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Tipo de pagamento (BOLETO, CARTAO, PIX, TRANSFERENCIA): ");
                        String tipoStr = scanner.nextLine().toUpperCase();

                        System.out.print("Status do pagamento (PENDENTE, PAGO, ATRASADO): ");
                        String statusStr = scanner.nextLine().toUpperCase();

                        Pagamento.TipoPagamento tipoPagamento = Pagamento.TipoPagamento.fromString(tipoStr);
                        Pagamento.StatusPagamento statusPagamento = Pagamento.StatusPagamento.fromString(statusStr);

                        Pagamento pagamento = new Pagamento(
                                valor,
                                LocalDateTime.now(),
                                tipoPagamento,
                                statusPagamento,
                                moradorId
                        );

                        pagamentoController.registrarPagamento(pagamento);
                        break;

                    case 2:
                        pagamentoController.listarPagamentos();
                        break;

                    case 3:
                        System.out.print("ID do pagamento: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Novo status (PENDENTE, PAGO, ATRASADO): ");
                        String novoStatusStr = scanner.nextLine().toUpperCase();

                        Pagamento.StatusPagamento novoStatus = Pagamento.StatusPagamento.fromString(novoStatusStr);

                        pagamentoController.atualizarStatusPagamento(idAtualizar, novoStatus);
                        break;

                    case 4:
                        System.out.print("ID do pagamento a excluir: ");
                        int idExcluir = scanner.nextInt();
                        pagamentoController.excluirPagamento(idExcluir);
                        break;

                    case 0:
                        System.out.println("Voltando...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 0);

            scanner.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
