package view;

import config.DatabaseConfig;
import controller.ReservaController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservaView {
    public static void ReservaMenu() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            ReservaController controller = new ReservaController(connection);
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\n===== Menu de Reservas =====");
                System.out.println("1. Cadastrar Reserva");
                System.out.println("2. Listar Reservas");
                System.out.println("3. Buscar Reserva por ID");
                System.out.println("4. Atualizar Reserva");
                System.out.println("5. Excluir Reserva");
                System.out.println("6. Listar Reservas por ID de Morador"); 
                System.out.println("7. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        System.out.print("ID do Morador: ");
                        int moradorId = scanner.nextInt();

                        System.out.print("ID da Área Comum: ");
                        int areaId = scanner.nextInt();
                        scanner.nextLine(); 

                        System.out.print("Data da Reserva (formato yyyy-MM-dd): ");
                        String dataInput = scanner.nextLine();
                        Date dataReserva = converterStringParaDate(dataInput);

                        System.out.print("Status (PENDENTE, CONFIRMADA, CANCELADA): ");
                        String status = scanner.nextLine().toUpperCase();

                        controller.cadastrarReserva(moradorId, areaId, dataReserva, status);
                        break;

                    case 2:
                        controller.listarReservas();
                        break;

                    case 3:
                        System.out.print("ID da Reserva: ");
                        int idBusca = scanner.nextInt();
                        controller.buscarReservaPorId(idBusca);
                        break;

                    case 4:
                        System.out.print("ID da Reserva a atualizar: ");
                        int idAtualizar = scanner.nextInt();

                        System.out.print("Novo ID do Morador: ");
                        int novoMoradorId = scanner.nextInt();

                        System.out.print("Novo ID da Área Comum: ");
                        int novaAreaId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nova Data da Reserva (yyyy-MM-dd): ");
                        String novaDataInput = scanner.nextLine();
                        Date novaData = converterStringParaDate(novaDataInput);

                        System.out.print("Novo Status (PENDENTE, CONFIRMADA, CANCELADA): ");
                        String novoStatus = scanner.nextLine().toUpperCase();

                        controller.atualizarReserva(idAtualizar, novoMoradorId, novaAreaId, novaData, novoStatus);
                        break;

                    case 5:
                        System.out.print("ID da Reserva a excluir: ");
                        int idExcluir = scanner.nextInt();
                        controller.excluirReserva(idExcluir);
                        break;

                    case 6:
                        System.out.print("Informe o ID do Morador: ");
                        int idMorador = scanner.nextInt();
                        controller.listarReservasPorMorador(idMorador);
                        break;

                    case 7:
                        System.out.println("Saindo do menu de reservas...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

            } while (opcao != 7);

        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    private static Date converterStringParaDate(String dataString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException("Data inválida. Use o formato yyyy-MM-dd.");
        }
    }
}
