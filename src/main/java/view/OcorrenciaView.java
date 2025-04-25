package view;

import config.DatabaseConfig;
import controller.MoradorController;
import controller.OcorrenciaController;
import model.Ocorrencia;
import model.Morador;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OcorrenciaView {

    public static void menuOcorrencias() {
        System.out.println("🔗 Iniciando conexão com o banco de dados...");
        DatabaseConfig.criarTabelas(); // Se você tiver um método assim, já cria as tabelas
        Connection connection;

        try {
            connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }

        OcorrenciaController ocorrenciaController = new OcorrenciaController();
        MoradorController moradorController = new MoradorController(connection);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📋 Menu de Ocorrências:");
            System.out.println("1 - Cadastrar Ocorrência");
            System.out.println("2 - Listar Ocorrências");
            System.out.println("3 - Atualizar Ocorrência");
            System.out.println("4 - Excluir Ocorrência");
            System.out.println("5 - Buscar por ID");
            System.out.println("6 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarOcorrencia(scanner, ocorrenciaController, moradorController);
                    break;
                case 2:
                    listarOcorrencias(ocorrenciaController);
                    break;
                case 3:
                    atualizarOcorrencia(scanner, ocorrenciaController);
                    break;
                case 4:
                    deletarOcorrencia(scanner, ocorrenciaController);
                    break;
                case 5:
                buscarOcorrenciaPorId(scanner, ocorrenciaController);
                    break;
                case 6:
                    System.out.println("🔙 Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("⚠️ Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarOcorrencia(Scanner scanner, OcorrenciaController controller, MoradorController moradorController) {
        System.out.print("Descrição da ocorrência: ");
        String descricao = scanner.nextLine();

        System.out.print("Status (Aberto, Em Andamento, Resolvido): ");
        String status = scanner.nextLine();

        System.out.print("ID do morador: ");
        int moradorId = scanner.nextInt();
        scanner.nextLine();

        Morador morador = moradorController.obterMoradorPorId(moradorId);
        if (morador == null) {
            System.out.println("❌ Morador com esse ID não existe. Cadastro cancelado.");
            return;
        }

        Ocorrencia ocorrencia = new Ocorrencia(descricao, LocalDateTime.now(),
                Ocorrencia.EstadoOcorrencia.fromString(status), morador);

        try {
            controller.adicionarOcorrencia(ocorrencia);
            System.out.println("✅ Ocorrência cadastrada com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void listarOcorrencias(OcorrenciaController controller) {
        try {
            List<Ocorrencia> lista = controller.listarOcorrencias();
            if (lista.isEmpty()) {
                System.out.println("Nenhuma ocorrência cadastrada.");
            } else {
                for (Ocorrencia o : lista) {
                    System.out.println(o);
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao listar: " + e.getMessage());
        }
    }

    private static void atualizarOcorrencia(Scanner scanner, OcorrenciaController controller) {
        System.out.print("ID da ocorrência a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nova descrição: ");
        String novaDescricao = scanner.nextLine();

        System.out.print("Novo status (Aberto, Em Andamento, Resolvido): ");
        String novoStatus = scanner.nextLine();

        System.out.print("Novo ID do morador: ");
        int novoMoradorId = scanner.nextInt();
        scanner.nextLine();

        Morador morador = new Morador();
        morador.setId(novoMoradorId);

        Ocorrencia ocorrencia = new Ocorrencia(novaDescricao, LocalDateTime.now(),
                Ocorrencia.EstadoOcorrencia.fromString(novoStatus), morador);
        ocorrencia.setId(id);

        try {
            controller.atualizarOcorrencia(ocorrencia);
            System.out.println("✅ Ocorrência atualizada com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar: " + e.getMessage());
        }
    }

    private static void deletarOcorrencia(Scanner scanner, OcorrenciaController controller) {
        System.out.print("ID da ocorrência a excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            controller.deletarOcorrencia(id);
            System.out.println("✅ Ocorrência excluída com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao excluir: " + e.getMessage());
        }
    }

    private static void buscarOcorrenciaPorId(Scanner scanner, OcorrenciaController controller) {
        System.out.print("ID da ocorrência: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Ocorrencia ocorrencia = controller.buscarOcorrenciaPorId(id);
            if (ocorrencia != null) {
                System.out.println("🔍 Ocorrência encontrada:");
                System.out.println(ocorrencia);
            } else {
                System.out.println("❗ Nenhuma ocorrência encontrada com esse ID.");
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar: " + e.getMessage());
        }
    }
}
