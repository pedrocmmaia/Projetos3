package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.stream.Stream;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/CondFacil";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void executarScriptSQL(Path arquivoSQL) {

        try {
            String sql = Files.readString(arquivoSQL);
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

    public static void criarTabelas() {

        Path diretorioSQL = Path.of("src", "main", "java", "sql");

        try(Stream<Path> arquivos = Files.list(diretorioSQL)) {
            arquivos.filter(arquivo ->arquivo.toString().toLowerCase().endsWith(".sql"))
                    .forEach(DatabaseConfig::executarScriptSQL);
        } catch (IOException e) {
            System.err.println("Erro ao listar arquivos SQL no diretório: " + diretorioSQL);
            e.printStackTrace();
        }
    }
}
