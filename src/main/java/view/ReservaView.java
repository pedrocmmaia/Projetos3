package view;

import config.DatabaseConfig;
import controller.MoradorController;
import controller.ReservaController;
import model.Morador;
import model.Reserva;
import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class ReservaView {
    public static void ReservaMenu(Usuario usuarioLogado) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            MoradorController moradorController = new MoradorController(connection);
            ReservaController reservaController = new ReservaController(connection);
            Morador morador = moradorController.obterMoradorPorUsuarioId(usuarioLogado.getId());
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


                        if (morador == null) {
                            System.out.println("❌ Morador não encontrado. Cadastro cancelado.");
                            break;
                        }
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
                        if (dataReserva.isBefore(LocalDate.now())){
                            System.out.println("A data da reserva deve ser diferente da data de hoje!");
                            break;
                        }
                        novaReserva.setDataReserva(dataReserva);

                        Reserva novaResrva = new Reserva(
                                morador.getId(),
                                areaId,
                                dataReserva,
                                Reserva.StatusReserva.PENDENTE
                        );
                        reservaController.cadastrarReserva(novaResrva);
                        break;

                    case 2:
                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.MORADOR){
                            reservaController.listarReservasPorMorador(morador.getId());
                        } else {
                        reservaController.listarReservas();
                        }
                        break;

                    case 3:
                        System.out.print("ID da Reserva: ");
                        int idBusca = scanner.nextInt();

                        Reserva encontrdada = reservaController.buscarReservaPorId(idBusca);
                        if (encontrdada == null){
                            System.out.println("Reserva não encontrada");
                            break;
                        }
                        reservaController.buscarReservaPorId(idBusca);
                        break;

                    case 4:
                        System.out.print("ID da Reserva a atualizar: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine();

                        Reserva reservaExistente = reservaController.buscarReservaPorId(idAtualizar);
                        if (reservaExistente == null) {
                            System.out.println("Reserva não encontrada");
                        }

                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.MORADOR &&
                            reservaController.pertenceAoUsuario(idAtualizar, usuarioLogado.getId())) {
                            System.out.println("Você não tem permissão para atualizar esta reserva!");
                            break;
                        }

                        System.out.print("Ano: ");
                        int anoAtualizado = scanner.nextInt();
                        System.out.print("Mês: ");
                        int mesAtualizado = scanner.nextInt();
                        System.out.print("Dia: ");
                        int diaAtualizad = scanner.nextInt();
                        LocalDate novaDataReserva = LocalDate.of(anoAtualizado, mesAtualizado, diaAtualizad);
                        if (novaDataReserva.isBefore(LocalDate.now())) {
                            System.out.println("A data da reserva deve ser diferente da data de hoje!");
                            break;
                        }

                        if (usuarioLogado.getTipoUsario() == Usuario.TipoUsuario.ADMINISTRADOR){
                            System.out.println("Novo status da reserva:");
                            System.out.println("1 - PENDENTE");
                            System.out.println("2 - CONFIRMADA");
                            System.out.println("3 - CANCELADA");

                            int tipoStatusOpcaoModificar = scanner.nextInt();
                            scanner.nextLine();

                            Reserva.StatusReserva tipoStatusModificar = switch (tipoStatusOpcaoModificar){
                                case 1 -> Reserva.StatusReserva.PENDENTE;
                                case 2 -> Reserva.StatusReserva.CONFIRMADA;
                                case 3 -> Reserva.StatusReserva.CANCELADA;
                                default -> reservaExistente.getStatusReserva();
                            };

                            Reserva reservaModificada = new Reserva(
                                    reservaExistente.getId(),
                                    reservaExistente.getMoradorId(),
                                    reservaExistente.getAreaId(),
                                    novaDataReserva,
                                    tipoStatusModificar

                            );
                            reservaController.atualizarReserva(reservaModificada);
                        } else {
                            Reserva reservaModificada = new Reserva(
                                    reservaExistente.getId(),
                                    reservaExistente.getMoradorId(),
                                    reservaExistente.getAreaId(),
                                    novaDataReserva,
                                    reservaExistente.getStatusReserva()

                            );
                            reservaController.atualizarReserva(reservaModificada);
                        }
                        System.out.println("✅ Reserva atualizada.");
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

}
