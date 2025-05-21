package view;

import config.DatabaseConfig;
import controller.ComunicadoController;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ComunicadoView {

    public static void menuComunicado(Usuario usuarioLogado) {
        try {
            Connection connection = DatabaseConfig.getConnection();
            Scanner scanner = new Scanner(System.in);

            ComunicadoController comunicadoController = new ComunicadoController(connection);

            int opcao;
            do {
                System.out.println("\n--- MENU COMUNICADO ---");
                System.out.println("1. Criar comunicado");
                System.out.println("2. Listar comunicados");
                System.out.println("3. Buscar comunicado por ID");
                System.out.println("4. Atualizar comunicado");
                System.out.println("5. Excluir comunicado");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = lerInt(scanner);

                switch (opcao) {
                    case 1:
                        criarComunicado(scanner, comunicadoController);
                        break;
                    case 2:
                        comunicadoController.listarComunicados();
                        break;
                    case 3:
                        buscarComunicado(scanner, comunicadoController);
                        break;
                    case 4:
                        atualizarComunicado(scanner, comunicadoController);
                        break;
                    case 5:
                        excluirComunicado(scanner, comunicadoController);
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

    private static void criarComunicado(Scanner scanner, ComunicadoController controller) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Conteúdo: ");
        String conteudo = scanner.nextLine();

        controller.cadastrarComunicado(titulo, conteudo);
    }

    private static void buscarComunicado(Scanner scanner, ComunicadoController controller) {
        System.out.print("ID do comunicado: ");
        int id = lerInt(scanner);
        controller.buscarComunicadoPorId(id);
    }

    private static void atualizarComunicado(Scanner scanner, ComunicadoController controller) {
        System.out.print("ID do comunicado: ");
        int id = lerInt(scanner);
        System.out.print("Novo título: ");
        String titulo = scanner.nextLine();
        System.out.print("Novo conteúdo: ");
        String conteudo = scanner.nextLine();

        controller.atualizarComunicado(id, titulo, conteudo);
    }

    private static void excluirComunicado(Scanner scanner, ComunicadoController controller) {
        System.out.print("ID do comunicado a excluir: ");
        int id = lerInt(scanner);
        controller.excluirComunicado(id);
    }

    private static int lerInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}
