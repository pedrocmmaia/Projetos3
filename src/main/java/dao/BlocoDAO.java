package dao;

import model.Bloco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlocoDAO {

    private Connection connection;

    public BlocoDAO(Connection connection){
        this.connection = connection;
    }

    public void cadastrarBloco(Bloco bloco) throws SQLException{
        String sql = "INSERT INTO bloco (nome) VALUES (?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, bloco.getNome());

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bloco.setId(rs.getInt("id"));
                    System.out.println("Bloco cadastrado com sucesso com ID: " + bloco.getId());
                }else {
                    System.err.println("Erro ao obter o ID do bloco cadastrado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Bloco buscarBlocoPorId(int id) throws SQLException{
        String sql = "SELECT * FROM bloco WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return  new Bloco(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
            }
        }
        return null;
    }

    public List<Bloco> listarBlocos() throws  SQLException{
        List<Bloco> blocos = new ArrayList<>();
        String sql = "SELECT * FROM bloco";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                blocos.add(new Bloco(
                        rs.getInt("id"),
                        rs.getString("nome"))
                );
            }
        }
        return blocos;
    }

    public void atualizarBloco(Bloco bloco) throws SQLException{
        String sql = "UPDATE bloco SET nome = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, bloco.getNome());
            stmt.setInt(2, bloco.getId());

            stmt.executeUpdate();
        }
    }

    public void excluirBloco(int id) throws SQLException{
        String sql = "DELETE FROM bloco WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public boolean existeBlocoComId(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM bloco WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
}