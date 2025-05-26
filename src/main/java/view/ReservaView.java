package view;

import config.DatabaseConfig;
import controller.ReservaController;
import model.Reserva;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservaView {
    public static void ReservaMenu(Usuario usuarioLogado) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            ReservaController reservaController = new ReservaController(connection);
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
                        Reserva novaReserva = new Reserva();

                        System.out.print("ID da Área Comum: ");
                        int areaId = scanner.nextInt();
                        scanner.nextLine(); 

                        novaReserva.setAreaId(areaId);

                        System.out.print("Ano: ");
                        int ano = scanner.nextInt();
                        System.out.print("Mês: ");
                        int mes = scanner.nextInt();
                        System.out.print("Dia: ");
                        int dia = scanner.nextInt();

                        LocalDate dataReserva = LocalDate.of(ano, mes, dia);
                        novaReserva.setDataReserva(dataReserva);

                        Reserva novaResrva = new Reserva(
                                usuarioLogado.getId(),
                                areaId,
                                dataReserva,
                                Reserva.StatusReserva.PENDENTE
                        );
                        reservaController.cadastrarReserva(novaResrva);
                        System.out.println("Reserva cadastrada com sucesso!");
                        break;

                    case 2:
                        reservaController.listarReservas();
                        break;

                    case 3:
                        System.out.print("ID da Reserva: ");
                        int idBusca = scanner.nextInt();
                        reservaController.buscarReservaPorId(idBusca);
                        break;

                    case 4:
                        Reserva reservaAtualizada = new Reserva();

                        System.out.print("ID da Reserva a atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        Reserva reservaExistente = reservaController.buscarReservaPorId(idAtualizar);
                        if (reservaExistente == null) {
                            System.out.println("Reserva não encontrada");
                        }

                        reservaAtualizada.setId(idAtualizar);

                        System.out.print("Ano: ");
                        int anoAtualizado = scanner.nextInt();
                        System.out.print("Mês: ");
                        int mesAtualizado = scanner.nextInt();
                        System.out.print("Dia: ");
                        int diaAtualizad = scanner.nextInt();

                        LocalDate novaDataReserva = LocalDate.of(anoAtualizado, mesAtualizado, diaAtualizad);
                        reservaAtualizada.setDataReserva(novaDataReserva);


                        reservaController.atualizarReserva(reservaAtualizada);
                        break;

                    case 5:
                        System.out.print("ID da Reserva a excluir: ");
                        int idExcluir = scanner.nextInt();
                        reservaController.excluirReserva(idExcluir);
                        break;

                    case 6:
                        System.out.print("Informe o ID do Morador: ");
                        int idMorador = scanner.nextInt();
                        reservaController.listarReservasPorMorador(idMorador);
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
