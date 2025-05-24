package view;

import config.DatabaseConfig;
import controller.ApartamentoController;
import controller.BlocoController;
import dao.BlocoDAO;
import model.Usuario;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ApartamentoBlocoView {
    public static void ApartamentoBlocoMenu(Usuario usuarioLogado) {
        try {

            Connection connection = DatabaseConfig.getConnection();
            BlocoDAO blocoDAO = new BlocoDAO(connection);
            BlocoController blocoController = new BlocoController(connection);
            ApartamentoController apartamentoController = new ApartamentoController(connection, blocoDAO);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n Menu Principal:");
                System.out.println("1 - Gerenciar Blocos");
                System.out.println("2 - Gerenciar Apartamentos");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        menuBlocos(scanner, blocoController);
                        break;
                    case 2:
                        menuApartamentos(scanner, apartamentoController, blocoController);
                        break;
                    case 3:
                        System.out.println(" Encerrando o programa...");
                        scanner.close();
                        return;
                    default:
                        System.out.println(" Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void menuBlocos(Scanner scanner, BlocoController blocoController) {
        while (true) {
            System.out.println("\n Gerenciamento de Blocos:");
            System.out.println("1 - Cadastrar Bloco");
            System.out.println("2 - Listar Blocos");
            System.out.println("3 - Atualizar Bloco");
            System.out.println("4 - Excluir Bloco");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Bloco: ");
                    String nome = scanner.nextLine();
                    blocoController.cadastrarBloco(nome);
                    break;
                case 2:
                    System.out.println("\n Lista de Blocos:");
                    blocoController.listarBlocos();
                    break;
                case 3:
                    System.out.print("ID do Bloco a atualizarOcorrenciaDao: ");
                    blocoController.listarBlocos();
                    int idBloco = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Nome do Bloco: ");
                    String novoNome = scanner.nextLine();
                    blocoController.atualizarBloco(idBloco, novoNome);
                    break;
                case 4:
                    System.out.print("ID do Bloco a excluir: ");
                    blocoController.listarBlocos();
                    int idExcluir = scanner.nextInt();
                    blocoController.excluirBloco(idExcluir);
                    break;
                case 5:
                    return;
                default:
                    System.out.println(" Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuApartamentos(Scanner scanner, ApartamentoController apartamentoController, BlocoController blocoController) {
        while (true) {
            System.out.println("\n🏠 Gerenciamento de Apartamentos:");
            System.out.println("1 - Cadastrar Apartamento");
            System.out.println("2 - Listar Apartamentos");
            System.out.println("3 - Atualizar Apartamento");
            System.out.println("4 - Excluir Apartamento");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Número do Apartamento: ");
                    int numero = scanner.nextInt();
                    System.out.print("Andar: ");
                    int andar = scanner.nextInt();

                    boolean cadastro = false;
                    while (!cadastro){
                        blocoController.listarBlocos();
                        System.out.println("Informe o ID do bloco:");
                        int blocoId = scanner.nextInt();

                        System.out.print("ID do morador responsável (ou 0 para nenhum): ");
                        int moradorResponsavelId = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            apartamentoController.cadastrarApartamento(numero, andar, blocoId, moradorResponsavelId != 0 ? moradorResponsavelId : null);
                            System.out.println("Apartamento cadastrado com sucesso!");
                            cadastro = true;
                        } catch (SQLException e) {
                            System.err.println("Erro no banco de dados: " +  e.getMessage());
                            System.out.println("Tente novamente ou verifique a conexão com o banco de dados.");
                        } catch (Exception e) {
                            System.err.println("Erro inesperado: " + e.getMessage());
                            e.printStackTrace();
                            System.out.println("Tente novamente.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n📋 Lista de Apartamentos:");
                    apartamentoController.listarApartamentos();
                    break;

                case 3:
                    System.out.print("ID do Apartamento a atualizarOcorrenciaDao: ");
                    int idApto = scanner.nextInt();
                    System.out.print("Novo Número do Apartamento: ");
                    int novoNumero = scanner.nextInt();
                    System.out.print("Novo Andar: ");
                    int novoAndar = scanner.nextInt();
                    System.out.print("Novo ID do Bloco: ");
                    int novoBlocoId = scanner.nextInt();
                    System.out.print("ID do novo Morador Responsável (ou 0 para nenhum): ");
                    int moradorResponsavelId = scanner.nextInt();

                    apartamentoController.atualizarApartamento(
                            idApto,
                            novoNumero,
                            novoAndar,
                            novoBlocoId,
                            moradorResponsavelId == 0 ? null : moradorResponsavelId
                    );
                    break;

                case 4:
                    System.out.print("ID do Apartamento a excluir: ");
                    int idExcluir = scanner.nextInt();
                    apartamentoController.excluirApartamento(idExcluir);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }
}
