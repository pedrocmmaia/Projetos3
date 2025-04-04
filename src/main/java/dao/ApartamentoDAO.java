package dao;

import config.DatabaseConfig;
import model.Apartamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {
    private  Connection connection;

    public  ApartamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarApartamento(Apartamento apartamento) throws SQLException{
        String sql = "INSERT INTO apartamento (numero, andar, bloco_id) VALUES (?, ?, ?) ";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, apartamento.getNumero());
            stmt.setInt(2, apartamento.getAndar());
            stmt.setInt(3, apartamento.getBlocoId());
            stmt.executeUpdate();
            System.out.println("Apartamento cadastrado com sucesso");

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                apartamento.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public  Apartamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM apartamento WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Apartamento(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("andar"),
                        rs.getInt("bloco_id")
                );
            }
        }
        return  null;
    }

    public List<Apartamento> listarApartamentos() throws  SQLException{
        List<Apartamento> apartamentos = new ArrayList<>();
        String sql = "SELECT * FROM apartamento";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                apartamentos.add(new Apartamento(
                        rs.getInt("id"),
                        rs.getInt("numero"),
                        rs.getInt("andar"),
                        rs.getInt("bloco_id")));
            }
        }
        return apartamentos;
    }

    public void atualizarApartamento(Apartamento apartamento) throws SQLException{
        String sql = "UPDATE apartamento SET numero = ?, andar = ?, bloco_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, apartamento.getNumero());
            stmt.setInt(2, apartamento.getAndar());
            stmt.setInt(3, apartamento.getBlocoId());
            stmt.setInt(4, apartamento.getId());
            stmt.executeUpdate();

        }
    }

    public void excluirApartamento(int id) throws SQLException{
        String sql = "DELETE FROM apartamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
