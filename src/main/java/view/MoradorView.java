package view;

import java.util.Scanner;
import model.Usuario;

public class MoradorView {
    public static void menuMorador(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU MORADOR =====");
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("1. Editar dados da conta");
            System.out.println("2. CRUD Ocorrências");
            System.out.println("3. CRUD Reserva");
            System.out.println("4. Visualizar cobranças");
            System.out.println("5. Comunicados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> UsuarioView.UsuarioMenu();
                case 2 -> OcorrenciaView.menuOcorrencias();
                //case 3 -> ReservasView.menuReservas();
                //case 4 -> CobrancasView.menuCobrancas();
                case 5 -> ComunicadoView.menuComunicado();
                //case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
