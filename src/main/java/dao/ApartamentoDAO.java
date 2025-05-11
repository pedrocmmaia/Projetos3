package dao;


import model.Apartamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {
    private  Connection connection;
    private BlocoDAO blocoDAO;

    public  ApartamentoDAO(Connection connection, BlocoDAO blocoDAO) {
        this.blocoDAO = blocoDAO;
        this.connection = connection;
    }

    public void cadastrarApartamento(Apartamento apartamento) throws SQLException{
        if (apartamento.getBlocoId() <= 0) {
            throw  new IllegalArgumentException("O ID do bloco não pode ser nulo!");
        }
        if (blocoDAO == null){
            throw new IllegalStateException("BlocoDAO não foi inicializado corretamente.");
        }
        if (!blocoDAO.existeBlocoComId(apartamento.getBlocoId())) {
            throw new SQLException("O bloco com o ID " + apartamento.getBlocoId() + " não existe.");
        }
        String sql = "INSERT INTO apartamento (numero, andar, bloco_id, morador_responsavel_id) VALUES (?, ?, ?, ?) ";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, apartamento.getNumero());
            stmt.setInt(2, apartamento.getAndar());
            stmt.setInt(3, apartamento.getBlocoId());

            if (apartamento.getMorador_responsavel_id() > 0){
                stmt.setInt(4, apartamento.getMorador_responsavel_id());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
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
                        rs.getInt("bloco_id"),
                        rs.getInt("morador_responsavel_id")
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
                        rs.getInt("bloco_id"),
                        rs.getInt("morador_responsavel_id")
                ));
            }
        }
        return apartamentos;
    }

    public void atualizarApartamento(Apartamento apartamento) throws SQLException{
        String sql = "UPDATE apartamento SET numero = ?, andar = ?, bloco_id = ?,morador_responsavel_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, apartamento.getNumero());
            stmt.setInt(2, apartamento.getAndar());
            stmt.setInt(3, apartamento.getBlocoId());

            if (apartamento.getMorador_responsavel_id() > 0) {
                stmt.setInt(4, apartamento.getMorador_responsavel_id());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            stmt.setInt(5, apartamento.getId());
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
