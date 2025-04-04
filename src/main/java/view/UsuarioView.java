package view;

import config.DatabaseConfig;
import controller.MoradorController;
import controller.SindicoController;
import controller.UsuarioController;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioView {
    public static void UsuarioMenu()  {
        DatabaseConfig.criarTabelas();
        Connection conexao = null;
        try {
            conexao = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UsuarioController usuarioController = new UsuarioController(conexao);
        MoradorController moradorController = new MoradorController(conexao);
        SindicoController sindicoController = new SindicoController(conexao);

        Scanner scanner =  new Scanner(System.in);

        while (true) {
            System.out.println("Menu");
            System.out.println("1 - Criar usuario");
            System.out.println("2 - Listar usuario");
            System.out.println("3 - Editar usuario");
            System.out.println("4 - Excluir usuario");
            System.out.println("5 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Nome: ");
                    String usuarioNome = scanner.nextLine();
                    //String email, String senha, String telefone, Usuario.Tipo tipo
                    System.out.println("Email: ");
                    String emailUsuario = scanner.nextLine();
                    System.out.println("Senha: ");
                    String senhaUsuario = scanner.nextLine();
                    System.out.println("Telefone: ");
                    String telefonfeUsuario = scanner.nextLine();
                    usuarioController.cadastrarUsuario(usuarioNome, emailUsuario, senhaUsuario, telefonfeUsuario, Usuario.Tipo.Morador);
            }
        }
    }
}
