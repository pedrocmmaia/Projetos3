package view;
import model.Usuario;
import java.util.Scanner;

public class SindicoView {
    public static void menuSindico(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU SÍNDICO =====");
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("1. Visualizar/Alterar Ocorrências");
            System.out.println("2. Visualizar/Aprovar/Negar Reservas");
            System.out.println("3. Visualizar/Aprovar/Negar Comunicados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> OcorrenciaView.menuOcorrencias();
                case 2 -> System.out.println("Acessando reservas...");
                case 3 -> ComunicadoView.menuComunicado();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
