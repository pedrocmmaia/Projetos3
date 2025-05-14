package view;

import config.DatabaseConfig;
import controller.*;
import dao.BlocoDAO;
import model.Usuario;
import service.AuthService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
        
    public static void main(String[] args) {
        System.out.println("Iniciando conexão com o banco de dados...");
        DatabaseConfig.criarTabelas();

        try (Connection connection = DatabaseConfig.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            BlocoDAO blocoDAO = new BlocoDAO(connection);
            BlocoController blocoController = new BlocoController(connection);
            ApartamentoController apartamentoController = new ApartamentoController(connection, blocoDAO);
            ComunicadoController comunicadoController = new ComunicadoController(connection);
            OcorrenciaController ocorrenciaController = new OcorrenciaController(connection);
            UsuarioController usuarioController = new UsuarioController(connection);
            LoginView LoginView = new LoginView(connection, scanner);

            int opcao;
            do {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Gerenciar Blocos");
                System.out.println("2. Gerenciar Apartamentos");
                System.out.println("3. Gerenciar Comunicados");
                System.out.println("4. Gerenciar Ocorrências");
                System.out.println("5. Gerenciar Usuários");
                System.out.println("6. Login");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = lerInt(scanner);

                
                switch (opcao) {
                    case 1 -> menuBlocos(scanner, blocoController);
                    case 2 -> menuApartamentos(scanner, apartamentoController, blocoController);
                    case 3 -> menuComunicado(scanner, comunicadoController);
                    case 4 -> menuOcorrencias(scanner, ocorrenciaController);
                    case 5 -> menuUsuarios(scanner, usuarioController);
                    case 6 -> {
                        System.out.println("====Digite o Email====");
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.println("====Digite a Senha====");
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        AuthService authService = new AuthService(connection);
                        Usuario usuarioLogado = authService.login(email, senha);

                        if (usuarioLogado != null) {
                            int menuOpcao;
                            do {
                                System.out.println("\n===== MENU USUÁRIO =====");
                                System.out.println("Bem-vindo, " + usuarioLogado.getNome());
                                System.out.println("Tipo: " + usuarioLogado.getTipoUsario());

                                switch (usuarioLogado.getTipoUsario()) {
                                    case MORADOR -> {
                                        System.out.println("1. Editar dados da conta");
                                        System.out.println("2. CRUD Ocorrências");
                                        System.out.println("3. CRUD Reserva");
                                        System.out.println("4. Visualizar cobranças");
                                        System.out.println("0. Sair");
                                    }
                                    case SINDICO -> {
                                        System.out.println("1. Visualizar/Alterar Ocorrências");
                                        System.out.println("2. Visualizar/Aprovar/Negar Reservas");
                                        System.out.println("0. Sair");
                                    }
                                    case ADMINISTRADOR -> {
                                        System.out.println("1. CRUD Financeiro");
                                        System.out.println("2. CRUD Usuário");
                                        System.out.println("3. CRUD Área Comum");
                                        System.out.println("4. Visualizar/Alterar Status Ocorrências");
                                        System.out.println("0. Sair");
                                    }
                                }

                                System.out.print("Escolha uma opção: ");
                                menuOpcao = lerInt(scanner);

                                switch (usuarioLogado.getTipoUsario()) {
                                    case MORADOR -> {
                                        switch (menuOpcao) {
                                            case 1 -> System.out.println("[Editar dados da conta]");
                                            case 2 -> System.out.println("[CRUD Ocorrências]");
                                            case 3 -> System.out.println("[CRUD Reserva]");
                                            case 4 -> System.out.println("[Visualizar cobranças]");
                                            case 0 -> System.out.println("Saindo...");
                                            default -> System.out.println("Opção inválida.");
                                        }
                                    }
                                    case SINDICO -> {
                                        switch (menuOpcao) {
                                            case 1 -> System.out.println("[Visualizar/Alterar Ocorrências]");
                                            case 2 -> System.out.println("[Visualizar/Aprovar/Negar Reservas]");
                                            case 0 -> System.out.println("Saindo...");
                                            default -> System.out.println("Opção inválida.");
                                        }
                                    }
                                    case ADMINISTRADOR -> {
                                        switch (menuOpcao) {
                                            case 1 -> System.out.println("[CRUD Financeiro]");
                                            case 2 -> System.out.println("[CRUD Usuário]");
                                            case 3 -> System.out.println("[CRUD Área Comum]");
                                            case 4 -> System.out.println("[Visualizar/Alterar Ocorrências]");
                                            case 0 -> System.out.println("Saindo...");
                                            default -> System.out.println("Opção inválida.");
                                        }
                                    }
                                }

                            } while (menuOpcao != 0);
                        } else {
                            System.out.println("Login falhou. Tente novamente.");
                        }
                    }
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
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
    
    private static void menuBlocos(Scanner scanner, BlocoController controller) {
        System.out.println("[MENU BLOCOS]");
    }

    private static void menuApartamentos(Scanner scanner, ApartamentoController controller, BlocoController blocoController) {
        System.out.println("[MENU APARTAMENTOS]");
    }

    private static void menuComunicado(Scanner scanner, ComunicadoController controller) {
        System.out.println("[MENU COMUNICADOS]");
    }

    private static void menuOcorrencias(Scanner scanner, OcorrenciaController controller) {
        System.out.println("[MENU OCORRÊNCIAS]");
    }

    private static void menuUsuarios(Scanner scanner, UsuarioController controller) {
        System.out.println("[MENU USUÁRIOS]");
    }
}