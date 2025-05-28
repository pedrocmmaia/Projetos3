package controller;

import dao.ReservaDAO;
import model.Reserva;
import model.Reserva.StatusReserva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaControllerTest {

    private ReservaDAO reservaDAO;
    private ReservaController reservaController;

    @BeforeEach
    void setUp() {
        reservaDAO = mock(ReservaDAO.class);
        reservaController = new ReservaController(reservaDAO);
    }

    @Test
    void deveImpedirReservaSeJaExisteNoMesmoDiaEArea() throws SQLException {
        Reserva reserva = new Reserva(1, 1, LocalDate.now(), StatusReserva.PENDENTE);
        when(reservaDAO.existeReservaNoDia(reserva.getAreaId(), reserva.getDataReserva())).thenReturn(true);

        Integer resultado = reservaController.cadastrarReserva(reserva);

        assertNull(resultado);
        verify(reservaDAO, never()).cadastrarReserva(reserva);
    }

    @Test
    void devePermitirCadastroSeNaoHouverReservaNoMesmoDiaEArea() throws SQLException {
        Reserva reserva = new Reserva(1, 1, LocalDate.now().plusDays(1), StatusReserva.PENDENTE);
        when(reservaDAO.existeReservaNoDia(reserva.getAreaId(), reserva.getDataReserva())).thenReturn(false);
        when(reservaDAO.cadastrarReserva(reserva)).thenReturn(123);

        Integer resultado = reservaController.cadastrarReserva(reserva);

        assertNotNull(resultado);
        assertEquals(123, resultado);
        verify(reservaDAO, times(1)).cadastrarReserva(reserva);
    }

    @Test
    void deveTratarExcecaoSQLExceptionNoCadastro() throws SQLException {
        Reserva reserva = new Reserva(1, 1, LocalDate.now().plusDays(2), StatusReserva.PENDENTE);
        when(reservaDAO.existeReservaNoDia(reserva.getAreaId(), reserva.getDataReserva())).thenThrow(new SQLException("Erro de conexão"));

        Integer resultado = reservaController.cadastrarReserva(reserva);

        assertNull(resultado);
        verify(reservaDAO, never()).cadastrarReserva(reserva);
    }
}
