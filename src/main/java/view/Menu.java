package view;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;


        do {
            System.out.println("\n=====================");
            System.out.println("CONDOMÍNIO - MENU PRINCIPAL");
            System.out.println("=====================");
            System.out.println("1 - Gerenciar Blocos e Apartamentos");
            System.out.println("2 - Gerenciar Comunicados");
            System.out.println("3 - Gerenciar Ocorrências");
            System.out.println("4 - Gerenciar Usuários");
            System.out.println("5 - Fazer Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInt(scanner);

            switch (opcao) {
                case 1:
                    ApartamentoBlocoView.ApartamentoBlocoMenu();
                    break;
            
                case 2:
                    ComunicadoView.menuComunicado();
                    break;
                
                case 3:
                    
                    break;
                default:
                    break;
            }


            
        } while (condition);

        scanner.close();
    }
    
    
}
