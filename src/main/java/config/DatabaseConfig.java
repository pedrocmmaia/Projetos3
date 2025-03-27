package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/CondFacil";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    // Método para obter a conexão com o banco
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para ler o arquivo SQL e executar o comando
    private static void executarScriptSQL(String arquivoSQL) {
        Path caminhoArquivo = Path.of("src", "sql", arquivoSQL);

        try {
            String sql = Files.readString(caminhoArquivo);
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
                System.out.println("Tabela criada com sucesso a partir do arquivo: " + arquivoSQL);
            } catch (SQLException e) {
                System.err.println("Erro ao executar o script SQL: " + arquivoSQL);
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo SQL: " + arquivoSQL);
            e.printStackTrace();
        }
    }

    // Método para criar as tabelas, chamando cada arquivo SQL de criação
    public static void criarTabelas() {
        executarScriptSQL("nome_da_tabela.sql"); // Crie a tabela na pasta SQL!!!
    }
}
