package dao;

import config.DatabaseConfig;
import model.Apartamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {

    // Método para cadastrar um novo apartamento
    public void cadastrarApartamento(Apartamento apartamento) {
        String sql = "INSERT INTO apartamento (numero, andar, bloco_id) VALUES (?, ?, ?)";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, apartamento.getNumero());
            pstmt.setInt(2, apartamento.getAndar());
            pstmt.setInt(3, apartamento.getBlocoId());
            pstmt.executeUpdate();

            // Recuperar o ID gerado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    apartamento.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("✅ Apartamento cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao cadastrar apartamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para listar todos os apartamentos
    public List<Apartamento> listarApartamentos() {
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT * FROM apartamento";

        try (Connection conexao = DatabaseConfig.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                apartamentos.add(new Apartamento(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("andar"),
                        rs.getInt("bloco_id")));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar apartamentos: " + e.getMessage());
            e.printStackTrace();
        }
        return apartamentos;
    }

    // Método para atualizar um apartamento
    public void atualizarApartamento(Apartamento apartamento) {
        String sql = "UPDATE apartamento SET numero = ?, andar = ?, bloco_id = ? WHERE id = ?";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, apartamento.getNumero());
            pstmt.setInt(2, apartamento.getAndar());
            pstmt.setInt(3, apartamento.getBlocoId());
            pstmt.setInt(4, apartamento.getId());
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✅ Apartamento atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum apartamento encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar apartamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para excluir um apartamento pelo ID
    public void excluirApartamento(int id) {
        String sql = "DELETE FROM apartamento WHERE id = ?";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✅ Apartamento excluído com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum apartamento encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir apartamento: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
