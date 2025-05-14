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

            // Instanciar controllers
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

                
            }
          }
     }
}