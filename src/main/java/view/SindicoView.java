package view;
import config.DatabaseConfig;
import model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;
import controller.UsuarioController;

public class SindicoView {
    public static void menuSindico(Usuario usuarioLogado) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController(DatabaseConfig.getConnection());

        int opcao;

        do {
            System.out.println("\n===== MENU SÍNDICO =====");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome());
            System.out.println("1. Menu Ocorrências");
            System.out.println("2. Menu Reservas");
            System.out.println("3. Menu Comunicados");
            System.out.println("4. Editar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                 OcorrenciaView.menuOcorrencias(usuarioLogado);
                break;

                case 2:
                 //ReservasView.menuReserva();
                break;

                case 3:
                 ComunicadoView.menuComunicado(usuarioLogado);
                break;

                case 4:
                    int idUsuario = usuarioLogado.getId();
                        Usuario existente = usuarioController.obterUsuarioPorId(idUsuario);
                    if (existente == null) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    System.out.print("Novo nome (" + existente.getNome() + "): ");
                    String nome = scanner.nextLine();
                    nome = nome.isEmpty() ? existente.getNome() : nome;

                    System.out.print("Novo email (" + existente.getEmail() + "): ");
                    String email = scanner.nextLine();
                    email = email.isEmpty() ? existente.getEmail() : email;

                    System.out.print("Nova senha: ");
                    String senha = scanner.nextLine();
                    senha = senha.isEmpty() ? existente.getSenha() : senha;

                    System.out.print("Novo telefone (" + existente.getTelefone() + "): ");
                    String telefone = scanner.nextLine();
                    telefone = telefone.isEmpty() ? existente.getTelefone() : telefone;

                    usuarioController.atualizarUsuario(
                            existente.getId(), nome, email, senha, telefone, existente.getTipoUsario()
                    );
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
                break;
            }
        } while (opcao != 0);
    }
    
}
