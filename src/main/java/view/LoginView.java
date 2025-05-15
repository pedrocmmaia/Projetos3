package view;

import config.DatabaseConfig;
import model.Usuario;
import service.AuthService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginView {

    public  static void loginMenu(){
        try {
            DatabaseConfig.criarTabelas();
            Connection connection = DatabaseConfig.getConnection();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            AuthService authService = new AuthService(connection);
            Usuario usuarioLogado = authService.login(email, senha);

            //Pra fazer esse switch tu tem q tirar desse if, pq caso o usuario logado for um sindico ou adm ele n entra
            // na condição
            //Ajsuta isso que da bom o codigo

            if (usuarioLogado != null) {
                
                switch (usuarioLogado.getTipoUsario()) {
                    case MORADOR -> MoradorView.menuMorador(usuarioLogado);
                    case SINDICO -> SindicoView.menuSindico(usuarioLogado);
                    case ADMINISTRADOR -> AdministradorView.menuAdministrador(usuarioLogado);
                    default -> System.out.println("Tipo de usuário desconhecido.");
                }
            } else {
                System.out.println("Login falhou. Email ou senha inválidos.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
