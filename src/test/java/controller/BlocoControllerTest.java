package controller;

import dao.BlocoDAO;
import model.Bloco;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    private Connection realConnection;
    private BlocoDAO realBlocoDAO;
    private BlocoController realBlocoController;

    @BeforeAll
    static void setupDriver() throws ClassNotFoundException {
        // Garante que o driver do H2 está carregado
        Class.forName("org.h2.Driver");
    }

    @BeforeEach
    void setUpIntegrationTest() throws SQLException {
        // Criando conexão real com banco H2 em memória
        realConnection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");

        // Criando a tabela "bloco"
        try (Statement stmt = realConnection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS bloco (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL" +
                    ")");
        }
        realBlocoDAO = new BlocoDAO(realConnection);
        realBlocoController = new BlocoController(realBlocoDAO);
    }

    @AfterEach
    void tearDownIntegrationTest() throws SQLException {
        // Limpando tabela após o teste
        try (Statement stmt = realConnection.createStatement()) {
            stmt.execute("DROP TABLE bloco");
        }
        realConnection.close();
    }

    @Test
    void testCadastrarEBuscarBlocoIntegracao() throws SQLException {
        String nomeBloco = "Bloco Integração";

        // Cadastrando bloco
        realBlocoController.cadastrarBloco(nomeBloco);

        // Buscando bloco diretamente
        Bloco blocoBuscado = realBlocoDAO.buscarBlocoPorId(1);

        // Validações
        assertNotNull(blocoBuscado);
        assertEquals(nomeBloco, blocoBuscado.getNome());
    }
}
