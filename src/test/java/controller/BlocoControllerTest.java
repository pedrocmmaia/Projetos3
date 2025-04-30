package controller;

import dao.BlocoDAO;
import model.Bloco;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BlocoControllerTest {

    @Mock
    private BlocoDAO blocoDAO;

    @Mock
    private Connection connection;

    @InjectMocks
    private BlocoController blocoController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        blocoController = new BlocoController(blocoDAO);
    }

    @Test
     void testCadstrarBlocoComSucesso() throws SQLException{
        String nomeBloco = "Bloco A";
        Bloco blocoCapturado = new Bloco(nomeBloco);
        blocoCapturado.setId(1);

        doNothing().when(blocoDAO).cadastrarBloco(any(Bloco.class));
        when(blocoDAO.buscarBlocoPorId(1)).thenReturn(blocoCapturado);

        blocoController.cadastrarBloco(nomeBloco);

        verify(blocoDAO, times(1)).cadastrarBloco(argThat(bloco -> bloco.getNome().equals(nomeBloco)));

    }
}
