package view;

import config.DatabaseConfig;
import controller.ComunicadoController;
import model.Comunicado;
import model.Ocorrencia;
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
                        int id = lerInt(scanner);
                        comunicadoController.buscarComunicadoPorId(id);
                        break;
                    case 4:
                        System.out.print("ID do comunicado: ");
                        int idComunicado = lerInt(scanner);
                        Comunicado comunicadoEncontrado = comunicadoController.buscarComunicadoPorId(idComunicado);

                        if (comunicadoEncontrado == null) {
                            System.out.println("❌ Comunicado não encontrada.");
                            break;
                        }

                        System.out.print("Novo título: ");
                        String tituloAlterado = scanner.nextLine();
                        tituloAlterado = tituloAlterado.isEmpty() ? comunicadoEncontrado.getTitulo() : tituloAlterado;
                        System.out.print("Novo conteúdo: ");
                        String conteudoAlterado = scanner.nextLine();
                        conteudoAlterado = conteudoAlterado.isEmpty() ? comunicadoEncontrado.getConteudo() : conteudoAlterado;

                        comunicadoController.atualizarComunicado(idComunicado, tituloAlterado, conteudoAlterado);
                        break;
                    case 5:
                        System.out.print("ID do comunicado a excluir: ");
                        int idExcluir = lerInt(scanner);

                        Comunicado comunicadoParaExcluir = comunicadoController.buscarComunicadoPorId(idExcluir);
                        if (comunicadoParaExcluir == null) {
                            System.out.println("❌ Comunicado não encontrada.");
                            break;
                        }

                        comunicadoController.excluirComunicado(idExcluir);
                        System.out.println("✅ Comunicado excluída.");
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
