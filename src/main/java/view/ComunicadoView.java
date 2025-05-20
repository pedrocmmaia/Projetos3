package view;

import config.DatabaseConfig;
import controller.ComunicadoController;
import dao.ComunicadoDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ComunicadoView {

    public static void menuComunicado() {
        try {
            System.out.println("🔗 Iniciando conexão com o banco de dados...");
            DatabaseConfig.criarTabelas();
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
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        scanner.nextLine();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Conteúdo: ");
                        String conteudo = scanner.nextLine();

                        comunicadoController.cadastrarComunicado(titulo, conteudo);
                        break;
                    case 2:
                        comunicadoController.listarComunicados();
                        break;
                    case 3:
                        System.out.print("ID do comunicado: ");
                        int idComunicadoBuscar = scanner.nextInt();
                        comunicadoController.buscarComunicadoPorId(idComunicadoBuscar);
                        break;
                    case 4:
                        scanner.nextLine();
                        System.out.print("ID do comunicado: ");
                        int idComunicadoEditar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo título: ");
                        String tituloNovo = scanner.nextLine();
                        System.out.print("Novo conteúdo: ");
                        String conteudoNovo = scanner.nextLine();
                        comunicadoController.atualizarComunicado(idComunicadoEditar, tituloNovo, conteudoNovo);
                        break;
                    case 5:
                        System.out.print("ID do comunicado a excluir: ");
                        int idExcluir = scanner.nextInt();
                        comunicadoController.excluirComunicado(idExcluir);
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
