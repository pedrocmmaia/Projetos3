package controller;

import dao.ReservaDAO;
import model.Reserva;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController(Connection connection) {
        this.reservaDAO = new ReservaDAO(connection);
    }

    public Integer cadastrarReserva(Reserva reserva) {
        try {
            Integer idGerado = reservaDAO.cadastrarReserva(reserva);
            if (idGerado != null) {
                reservaDAO.cadastrarReserva(reserva);
                System.out.println("Reserva cadastrada com sucesso! ID reserva: " + idGerado);
                return idGerado;
            }else {
                System.err.println("Erro ao cadastrar nova reserva: Falha ao obter o ID gerado");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar reserva: " + e.getMessage());
        }
        return null;
    }

    public Reserva buscarReservaPorId(int id) {
        try {
            Reserva reserva = reservaDAO.buscarReservaPorId(id);
            if (reserva != null) {
                System.out.println("Reserva encontrada: " + reserva);
                System.out.println("ID reserva: " + reserva.getId());
                System.out.println("ID moradorador: " + reserva.getMoradorId());
                System.out.println("ID area reservada: " + reserva.getAreaId());
                System.out.println("Data reserva: " + reserva.getDataReserva());
                System.out.println("Status reserva: " + reserva.getStatusReserva());
            } else {
                System.out.println("Reserva não encontrada.");
            }
            return reserva;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar reserva: " + e.getMessage());
            return null;
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

    public void atualizarReserva(Reserva reserva) {
        try {
            reservaDAO.atualizarReserva(reserva);
            System.out.println("Reserva atualizada com sucesso!");
        }catch (SQLException e) {
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
