package view;

import config.DatabaseConfig;
import controller.PagamentoController;
import model.Pagamento;
import model.Usuario;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PagamentoView {

    public static void PagamentoMenu(Usuario usuarioLogado) {
        DatabaseConfig.criarTabelas();
        try (Connection connection = DatabaseConfig.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            PagamentoController controller = new PagamentoController(connection);

            int opcao;

            do {
                System.out.println("\n==== Menu - Pagamento ====");
                System.out.println("1. Cadastrar novo pagamento");
                System.out.println("2. Buscar pagamento por ID");
                System.out.println("3. Listar todos os pagamentos");
                System.out.println("4. Atualizar um pagamento");
                System.out.println("5. Deletar um pagamento");
                System.out.println("0. Voltar/Encerrar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        Pagamento novoPagamento = new Pagamento();

                        System.out.print("Tipo de pagamento (CONDOMINIAL, IPTU, EXTRA): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        novoPagamento.setTipo(Pagamento.TipoPagamento.valueOf(tipo));

                        System.out.print("Valor do pagamento: ");
                        float valor = scanner.nextFloat();
                        novoPagamento.setValor(valor);

                        System.out.print("Data de vencimento (yyyy-MM-ddTHH:mm): ");
                        String dataStr = scanner.next();
                        LocalDateTime dataVencimento = LocalDateTime.parse(dataStr);
                        novoPagamento.setDataVencimento(dataVencimento);

                        System.out.print("Status do pagamento (PAGO, PENDENTE, ATRASADO): ");
                        String status = scanner.next().toUpperCase();
                        novoPagamento.setStatus(Pagamento.StatusPagamento.valueOf(status));

                        controller.cadastrarPagamento(novoPagamento);
                        break;

                    case 2:
                        System.out.print("ID do pagamento: ");
                        int idBusca = scanner.nextInt();
                        scanner.nextLine();
                        Pagamento pagamentoBuscado = controller.buscarPagamentoPorId(idBusca);
                        if (pagamentoBuscado != null) {
                            System.out.println(pagamentoBuscado);
                        } else {
                            System.out.println("Pagamento não encontrado.");
                        }
                        break;

                    case 3:
                        controller.listarPagamentos();
                        break;

                    case 4:
                        Pagamento pagamentoAtualizado = new Pagamento();

                        System.out.print("ID do pagamento para atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();
                        pagamentoAtualizado.setId(idAtualizar);

                        System.out.print("Novo tipo de pagamento (CONDOMINIAL, IPTU, EXTRA): ");
                        String novoTipo = scanner.nextLine().toUpperCase();
                        pagamentoAtualizado.setTipo(Pagamento.TipoPagamento.valueOf(novoTipo));

                        System.out.print("Novo valor do pagamento: ");
                        float novoValor = scanner.nextFloat();
                        pagamentoAtualizado.setValor(novoValor);

                        System.out.print("Nova data de vencimento (yyyy-MM-ddTHH:mm): ");
                        String novaDataStr = scanner.next();
                        LocalDateTime novaData = LocalDateTime.parse(novaDataStr);
                        pagamentoAtualizado.setDataVencimento(novaData);

                        System.out.print("Novo status do pagamento (PAGO, PENDENTE, ATRASADO): ");
                        String novoStatus = scanner.next().toUpperCase();
                        pagamentoAtualizado.setStatus(Pagamento.StatusPagamento.valueOf(novoStatus));

                        controller.atualizarPagamento(pagamentoAtualizado);
                        break;

                    case 5:
                        System.out.print("ID do pagamento para deletar: ");
                        int idDeletar = scanner.nextInt();
                        scanner.nextLine();
                        controller.deletarPagamento(idDeletar);
                        break;

                    case 0:
                        System.out.println("Retornando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } while (opcao != 0);

        } catch (Exception e) {
            System.err.println("Erro no menu de Pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
