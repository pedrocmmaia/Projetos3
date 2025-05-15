package view;
import model.Usuario;
import java.util.Scanner;
import controller.UsuarioController;

public class SindicoView {
    public static void menuSindico(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController();

        int opcao;

        do {
            System.out.println("\n===== MENU SÍNDICO =====");
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("1. Visualizar/Alterar Ocorrências");
            System.out.println("2. Visualizar/Aprovar/Negar Reservas");
            System.out.println("3. Visualizar/Aprovar/Negar Comunicados");
            System.out.println("4. Editar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                 OcorrenciaView.menuOcorrencias();
                break;
                 //case 2: 
                 //ReservasView.menuReserva();
                //break;
                 case 3:
                 ComunicadoView.menuComunicado();
                break;
                case 4:
                   int idUsuario = usuario.getId();
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
                 //case 0 :
                 // System.out.println("Saindo...");
                //break;
                default :
                 System.out.println("Opção inválida.");
                 break;
            }
        } while (opcao != 0);
    }
    
}
