package dao;

import model.Morador;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoradorDAO{
    private Connection connection;

    public MoradorDAO(Connection connection){
        this.connection = connection;
    }

    public Integer cadastrarMorador(Morador morador) throws SQLException{
        String sql = "INSERT INTO morador(usuario_id, apartamento_id) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, morador.getUsuarioId());
            stmt.setInt(2, morador.getApartamentoId());
            stmt.executeUpdate();
            System.out.println("Morador cadastrado com sucesso!");

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                morador.setId(rs.getInt(1));
            }
            return  null;
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }

    }

    public Morador buscarDadosMoradorPorId(int id) throws SQLException {
        String sql = "SELECT * FROM morador WHERE id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int usuarioId = rs.getInt("usuario_id");
                int apartamentoId = rs.getInt("apartamento_id");

                Morador morador = new Morador(usuarioId, apartamentoId);
                morador.setId(id);
                return morador;
            }
        }

        return null;
    }

    public List<Morador> listarMoradores() throws SQLException{
    List<Morador> moradores = new ArrayList<>();
    String sql = "SELECT * FROM morador";

    try(PreparedStatement statement = connection.prepareStatement(sql)){
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            moradores.add(new Morador(
                    rs.getInt("usuario_id"),
                    rs.getInt("apartamento_id")
            ));
            }
        }
        return moradores;
    }


//public void atualizarMorador(Morador morador) throws SQLException{
//    usuarioDAO.atualiarUsuario(morador);
//
//    String sql = "UPDATE morador SET apartamento_id = ? WHERE usuario_id = ?";
//
//    try(PreparedStatement statement = connection.prepareStatement(sql)){
//        statement.setInt(1, morador.getApartamentoId());
//        statement.setInt(2, morador.getId());
//        System.out.println("Morador atualizado com sucesso!");
//    }
//}

public void deletarMorador(int id) throws SQLException{
    String sql = "DELETE FROM morador WHERE id = ?";
    try(PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Morador deletado com sucesso!");
    }catch (SQLException e) {
        System.out.println("Erro ao deletar morador: " + e.getMessage());
    }
}
}