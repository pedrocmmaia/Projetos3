package view;

import controller.AreaComumController;
import config.DatabaseConfig;

import java.sql.Connection;
import java.util.Scanner;


public class AreaComumView {

    public static void AreaComumMenu() {
        DatabaseConfig.criarTabelas();
        try (Connection connection = DatabaseConfig.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            AreaComumController controller = new AreaComumController(connection);

            int opcao;

            do {
                System.out.println("\n==== Menu - Área Comum ====");
                System.out.println("1. Cadastrar nova área comum");
                System.out.println("2. Buscar área comum por ID");
                System.out.println("3. Listar todas as áreas comuns");
                System.out.println("4. Atualizar uma área comum");
                System.out.println("5. Excluir uma área comum");
                System.out.println("0. Voltar/Encerrar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Nome da área comum: ");
                        String nome = scanner.nextLine();
                        System.out.print("Está disponível? (true/false): ");
                        boolean disponibilidade = scanner.nextBoolean();
                        scanner.nextLine();
                        controller.cadastrarAreaComum(nome, disponibilidade);
                        break;
                    case 2:
                        System.out.print("ID da área comum: ");
                        int idBusca = scanner.nextInt();
                        scanner.nextLine();
                        controller.buscarAreaComumPorId(idBusca);
                        break;
                    case 3:
                        controller.listarAreasComuns();
                        break;
                    case 4:
                        System.out.print("ID da área comum para atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Está disponível? (true/false): ");
                        boolean novaDisponibilidade = scanner.nextBoolean();
                        scanner.nextLine();
                        controller.atualizarAreaComum(idAtualizar, novoNome, novaDisponibilidade);
                        break;
                    case 5:
                        System.out.print("ID da área comum para excluir: ");
                        int idExcluir = scanner.nextInt();
                        scanner.nextLine();
                        controller.excluirAreaComum(idExcluir);
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
            System.err.println("Erro no menu de Área Comum: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
