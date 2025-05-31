package view;
import config.DatabaseConfig;
import model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;
import controller.UsuarioController;

public class AdministradorView {
    public static void menuAdministrador(Usuario usuarioLogado) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController(DatabaseConfig.getConnection());
        int opcao;
    
        do {
            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome());
            System.out.println("1. Menu Financeiro");
            System.out.println("2. Menu Usuário");
            System.out.println("3. Menu Área Comum");
            System.out.println("4. Menu Ocorrências");
            System.out.println("5. Menu Comunicados");
            System.out.println("6. Menu Reservas");
            System.out.println("7. Editar meu dados");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1 :
                    PagamentoView.PagamentoMenu(usuarioLogado);
                break;

                case 2 :
                    UsuarioView.UsuarioMenu();
                break;

                case 3 :
                    AreaComumView.AreaComumMenu(usuarioLogado);
                break;

                case 4:
                    OcorrenciaView.menuOcorrencias(usuarioLogado);
                break;

                case 5:
                    ComunicadoView.menuComunicado(usuarioLogado);
                break;

                case 6:
                    ReservaView.ReservaMenu(usuarioLogado);
                break;

                case 7:
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
                            existente.getId(),
                            nome,
                            email,
                            senha,
                            telefone,
                            existente.getTipoUsario()
                    );
                break;

                case 8:
                    System.out.println("Saindo...");
                return;

                default:
                    System.out.println("Opção inválida.");
                break;
            }
        } while (opcao != 0);
    }
}
