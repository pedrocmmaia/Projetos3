package controller;

import dao.ReservaDAO;
import model.Reserva;
import model.StatusReserva;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController(Connection connection) {
        this.reservaDAO = new ReservaDAO(connection);
    }

    public void cadastrarReserva(int moradorId, int areaId, Date dataReserva, String statusStr) {
        try {
            StatusReserva status = StatusReserva.valueOf(statusStr.toUpperCase());
            Reserva reserva = new Reserva(moradorId, areaId, dataReserva, status);
            reservaDAO.cadastrarReserva(reserva);
            System.out.println("Reserva cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Status inválido. Use: PENDENTE, CONFIRMADA ou CANCELADA.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar reserva: " + e.getMessage());
        }
    }

    public void buscarReservaPorId(int id) {
        try {
            Reserva reserva = reservaDAO.buscarReservaPorId(id);
            if (reserva != null) {
                System.out.println("Reserva encontrada: " + reserva);
            } else {
                System.out.println("Reserva não encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar reserva: " + e.getMessage());
        }
    }

    public void listarReservas() {
        try {
            List<Reserva> reservas = reservaDAO.listarReservas();
            if (reservas.isEmpty()) {
                System.out.println("Nenhuma reserva encontrada.");
            } else {
                for (Reserva r : reservas) {
                    System.out.println(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar reservas: " + e.getMessage());
        }
    }

    public void listarReservasPorMorador(int moradorId) {
        try {
            List<Reserva> reservas = reservaDAO.listarReservasPorMorador(moradorId);
            if (reservas.isEmpty()) {
                System.out.println("Nenhuma reserva encontrada para o morador de ID " + moradorId);
            } else {
                System.out.println("Reservas do morador de ID " + moradorId + ":");
                for (Reserva r : reservas) {
                    System.out.println(r);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar reservas por morador: " + e.getMessage());
        }
    }

    public void atualizarReserva(int id, int moradorId, int areaId, Date dataReserva, String statusStr) {
        try {
            StatusReserva status = StatusReserva.valueOf(statusStr.toUpperCase());
            Reserva reserva = new Reserva(id, moradorId, areaId, dataReserva, status);
            reservaDAO.atualizarReserva(reserva);
            System.out.println("Reserva atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Status inválido. Use: PENDENTE, CONFIRMADA ou CANCELADA.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar reserva: " + e.getMessage());
        }
    }

    public void excluirReserva(int id) {
        try {
            reservaDAO.excluirReserva(id);
            System.out.println("Reserva excluída com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir reserva: " + e.getMessage());
        }
    }
}
