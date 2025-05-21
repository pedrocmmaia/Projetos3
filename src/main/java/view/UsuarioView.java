package view;

import config.DatabaseConfig;
import controller.*;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioView {

    public static void UsuarioMenu() {
        try {
            System.out.println("🔗 Iniciando conexão com o banco de dados...");
            DatabaseConfig.criarTabelas();
            Connection connection = DatabaseConfig.getConnection();
            Scanner scanner = new Scanner(System.in);

            UsuarioController usuarioController = new UsuarioController(connection);
            MoradorController moradorController = new MoradorController(connection);
            SindicoController sindicoController = new SindicoController(connection);
            AdministradorController administradorController = new AdministradorController(connection);

            int opcao;
            do {
                System.out.println("\n--- MENU PRINCIPAL ---");
                System.out.println("1. CRUD Usuário");
                System.out.println("2. CRUD Morador");
                System.out.println("3. CRUD Síndico");
                System.out.println("4. CRUD Administrador");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        crudUsuario(scanner, usuarioController, moradorController, sindicoController, administradorController);
                        break;
                    case 2:
                        crudMorador(scanner, moradorController);
                        break;
                    case 3:
                        crudSindico(scanner, sindicoController);
                        break;
                    case 4:
                        crudAdministrador(scanner, administradorController);
                        break;
                    case 0:
                        System.out.println("Saindo...");
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

    private static void crudUsuario(Scanner scanner, UsuarioController usuarioController, MoradorController moradorController, SindicoController sindicoController, AdministradorController administradorController) {
        System.out.println("--- CRUD Usuário ---");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Listar usuários");
        System.out.println("3 - Buscar por ID");
        System.out.println("4 - Atualizar usuário");
        System.out.println("5 - Deletar usuário");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();

                System.out.println("Tipo de usuário:");
                System.out.println("1 - MORADOR");
                System.out.println("2 - SINDICO");
                System.out.println("3 - ADMINISTRADOR");
                int tipoOpcao = scanner.nextInt();
                scanner.nextLine();

                Usuario.TipoUsuario tipo = null;
                switch (tipoOpcao) {
                    case 1: tipo = Usuario.TipoUsuario.MORADOR; break;
                    case 2: tipo = Usuario.TipoUsuario.SINDICO; break;
                    case 3: tipo = Usuario.TipoUsuario.ADMINISTRADOR; break;
                }

                Usuario usuarioCompleto = new Usuario(nome, email, senha, telefone, tipo);
                Integer usuarioId = usuarioController.cadastrarUsuario(usuarioCompleto);
                usuarioCompleto.setId(usuarioId);

                if (usuarioId != null) {
                    switch (tipo) {
                        case MORADOR:
                            System.out.print("ID do apartamento: ");
                            int aptoId = scanner.nextInt();
                            moradorController.cadastrarMorador(usuarioId, aptoId);
                            break;
                        case SINDICO:
                            sindicoController.cadastraSindico(usuarioId);
                            break;
                        case ADMINISTRADOR:
                            administradorController.cadastrarAdministrador(usuarioId);
                            break;
                    }
                }
                break;

            case 2:
                usuarioController.listarUsuarios();
                break;

            case 3:
                System.out.print("ID: ");
                int id = scanner.nextInt();
                usuarioController.buscarUsuarioPorId(id);
                break;

            case 4:
                System.out.print("Digite o ID do usuário para atualizar: ");
                int idUsuario = scanner.nextInt();
                scanner.nextLine();

                Usuario existente = usuarioController.obterUsuarioPorId(idUsuario);
                if (existente == null) {
                    System.out.println("Usuário não encontrado!");
                    break;
                }

                System.out.print("Novo nome (" + existente.getNome() + "): ");
                nome = scanner.nextLine();
                nome = nome.isEmpty() ? existente.getNome() : nome;

                System.out.print("Novo email (" + existente.getEmail() + "): ");
                email = scanner.nextLine();
                email = email.isEmpty() ? existente.getEmail() : email;

                System.out.print("Nova senha: ");
                senha = scanner.nextLine();
                senha = senha.isEmpty() ? existente.getSenha() : senha;

                System.out.print("Novo telefone (" + existente.getTelefone() + "): ");
                telefone = scanner.nextLine();
                telefone = telefone.isEmpty() ? existente.getTelefone() : telefone;

                usuarioController.atualizarUsuario(
                        existente.getId(), nome, email, senha, telefone, existente.getTipoUsario()
                );
                break;

            case 5:
                System.out.print("ID: ");
                int idDel = scanner.nextInt();
                usuarioController.deletarUsuario(idDel);
                break;
        }
    }

    private static void crudMorador(Scanner scanner, MoradorController controller) {
        System.out.println("--- CRUD Morador ---");
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Listar todos");
        System.out.println("3 - Deletar morador");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                System.out.print("ID: ");
                int id = scanner.nextInt();
                controller.buscarMoradorPorId(id);
                break;
            case 2:
                controller.listarMoradores();
                break;
            case 3:
                System.out.print("ID: ");
                int idDel = scanner.nextInt();
                controller.deletarMorador(idDel);
                break;
        }
    }

    private static void crudSindico(Scanner scanner, SindicoController controller) {
        System.out.println("--- CRUD Síndico ---");
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Listar todos");
        System.out.println("3 - Deletar síndico");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                System.out.print("ID: ");
                int id = scanner.nextInt();
                controller.buscarSindicoPorId(id);
                break;
            case 2:
                controller.listarSindicos();
                break;
            case 3:
                System.out.print("ID: ");
                int idDel = scanner.nextInt();
                controller.deletarSindico(idDel);
                break;
        }
    }

    private static void crudAdministrador(Scanner scanner, AdministradorController controller) {
        System.out.println("--- CRUD Administrador ---");
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Listar todos");
        System.out.println("3 - Deletar administrador");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                System.out.print("ID: ");
                int id = scanner.nextInt();
                controller.buscarAdministradorPorId(id);
                break;
            case 2:
                controller.listarAdministradores();
                break;
            case 3:
                System.out.print("ID: ");
                int idDel = scanner.nextInt();
                controller.deletarAdministrador(idDel);
                break;
        }
    }
}
