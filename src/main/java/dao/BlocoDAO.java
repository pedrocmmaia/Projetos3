package dao;

import config.DatabaseConfig;
import model.Bloco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlocoDAO {

    public void cadastrarBloco(Bloco bloco) {
        String sql = "INSERT INTO bloco (nome) VALUES (?) RETURNING id";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, bloco.getNome());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bloco.setId(rs.getInt("id"));
            }

            System.out.println("Bloco cadastrado com sucesso: " + bloco);

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar bloco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Listar todos os blocos cadastrados
    public List<Bloco> listarBlocos() {
        List<Bloco> blocos = new ArrayList<>();
        String sql = "SELECT * FROM bloco";

        try (Connection conexao = DatabaseConfig.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                blocos.add(new Bloco(rs.getInt("id"), rs.getString("nome")));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar blocos: " + e.getMessage());
            e.printStackTrace();
        }

        return blocos;
    }

    // Atualizar um bloco
    public void atualizarBloco(Bloco bloco) {
        String sql = "UPDATE bloco SET nome = ? WHERE id = ?";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, bloco.getNome());
            pstmt.setInt(2, bloco.getId());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Bloco atualizado com sucesso!");
            } else {
                System.out.println("Nenhum bloco encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar bloco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Excluir um bloco pelo ID
    public void excluirBloco(int id) {
        String sql = "DELETE FROM bloco WHERE id = ?";

        try (Connection conexao = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Bloco excluído com sucesso!");
            } else {
                System.out.println("Nenhum bloco encontrado com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir bloco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}