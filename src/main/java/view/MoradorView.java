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
            System.out.println("6. Apartamento Bloco");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("Editar dados da conta de " + usuario.getNome());
                case 2 -> OcorrenciaView.menuOcorrencias();
                case 3 -> System.out.println("Acessando CRUD de Reservas...");
                case 4 -> System.out.println("Visualizando cobranças...");
                case 5 -> ComunicadoView.menuComunicado();
                case 6 -> ApartamentoBlocoView.ApartamentoBlocoMenu();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
