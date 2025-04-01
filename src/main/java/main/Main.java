package main;

import config.DatabaseConfig;
import controller.ApartamentoController;
import controller.BlocoController;
import model.Apartamento;
import model.Bloco;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("🔗 Iniciando conexão com o banco de dados...");
        DatabaseConfig.criarTabelas();

        // Criar controllers
        BlocoController blocoController = new BlocoController();
        ApartamentoController apartamentoController = new ApartamentoController();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n🏢 Menu Principal:");
            System.out.println("1 - Gerenciar Blocos");
            System.out.println("2 - Gerenciar Apartamentos");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    menuBlocos(scanner, blocoController);
                    break;
                case 2:
                    menuApartamentos(scanner, apartamentoController, blocoController);
                    break;
                case 3:
                    System.out.println("🚪 Encerrando o programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu para gerenciar blocos
    private static void menuBlocos(Scanner scanner, BlocoController blocoController) {
        while (true) {
            System.out.println("\n🏢 Gerenciamento de Blocos:");
            System.out.println("1 - Cadastrar Bloco");
            System.out.println("2 - Listar Blocos");
            System.out.println("3 - Atualizar Bloco");
            System.out.println("4 - Excluir Bloco");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Bloco: ");
                    String nome = scanner.nextLine();
                    blocoController.cadastrarBloco(nome);
                    break;
                case 2:
                    List<Bloco> blocos = blocoController.listarBlocos();
                    System.out.println("\n📋 Lista de Blocos:");
                    blocos.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID do Bloco a atualizar: ");
                    int idBloco = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Nome do Bloco: ");
                    String novoNome = scanner.nextLine();
                    blocoController.atualizarBloco(idBloco, novoNome);
                    break;
                case 4:
                    System.out.print("ID do Bloco a excluir: ");
                    int idExcluir = scanner.nextInt();
                    blocoController.excluirBloco(idExcluir);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu para gerenciar apartamentos
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
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Número do Apartamento: ");
                    int numero = scanner.nextInt();
                    System.out.print("Andar: ");
                    int andar = scanner.nextInt();
                    System.out.print("ID do Bloco: ");
                    int blocoId = scanner.nextInt();
                    apartamentoController.cadastrarApartamento(numero, andar, blocoId);
                    break;
                case 2:
                    List<Apartamento> apartamentos = apartamentoController.listarApartamentos();
                    System.out.println("\n📋 Lista de Apartamentos:");
                    apartamentos.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID do Apartamento a atualizar: ");
                    int idApto = scanner.nextInt();
                    System.out.print("Novo Número do Apartamento: ");
                    int novoNumero = scanner.nextInt();
                    System.out.print("Novo Andar: ");
                    int novoAndar = scanner.nextInt();
                    System.out.print("Novo ID do Bloco: ");
                    int novoBlocoId = scanner.nextInt();
                    apartamentoController.atualizarApartamento(idApto, novoNumero, novoAndar, novoBlocoId);
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
