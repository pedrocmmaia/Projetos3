package view;
import model.Usuario;
import java.util.Scanner;

public class AdministradorView {
    public static void menuAdministrador(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
    
        do {
            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("1. CRUD Financeiro");
            System.out.println("2. CRUD Usuário");
            System.out.println("3. CRUD Área Comum");
            System.out.println("4. Visualizar/Alterar Status de Ocorrências");
            System.out.println("5. Visualizar/Aprovar/Negar Comunicados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                //case 1 -> FincanceiroView.menuFinanceiro();
                case 2 -> UsuarioView.UsuarioMenu();
               // case 3 -> AreaComunView.menuAreaComun();
                //case 4 -> OcorrenciaView.menuOcorrencias();
                case 5 -> ComunicadoView.menuComunicado();
                //case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
