package dao;

import model.Apartamento;
import model.Bloco;
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
            return  morador.getId();
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
                Morador morador =  new Morador(
                        rs.getInt("usuario_id"),
                        rs.getInt("apartamento_id")
                );
                morador.setId(rs.getInt("id"));
                return morador;
            }
        }

        return null;
    }

    public List<Morador> listarMoradores() throws SQLException{
    List<Morador> moradores = new ArrayList<>();
    String sql =   """
            SELECT
                m.id AS morador_id,

                u.id AS usuario_id,
                u.nome AS usuario_nome,
                u.email AS usuario_email,
                u.telefone AS usuario_telefone,
                u.tipo_usuario AS usuario_tipo,

                a.id AS apartamento_id,
                a.numero AS apartamento_numero,
                a.andar AS apartamento_andar,

                b.id AS bloco_id,
                b.nome AS bloco_nome

            FROM morador m
            JOIN usuario u ON m.usuario_id = u.id
            JOIN apartamento a ON m.apartamento_id = a.id
            JOIN bloco b ON a.bloco_id = b.id
        """;

    try(PreparedStatement statement = connection.prepareStatement(sql)){
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {

            Bloco bloco = new Bloco(
                    rs.getInt("bloco_id"),
                    rs.getString("bloco_nomew")
            );

            Apartamento apartamento =  new Apartamento(
                    rs.getInt("apartamento_id"),
                    rs.getInt("apartamento_numero"),
                    rs.getInt("apartamento_andar"),
                    bloco
            );

            Morador morador = new Morador(
                    rs.getInt("usuario_id"),
                    rs.getString("usuario_nome"),
                    rs.getString("usuario_email"),
                    null, // senha
                    rs.getString("usuario_telefone"),
                    Usuario.TipoUsuario.valueOf(rs.getString("usuario_tipo")),
                    apartamento
            );
            morador.setId(rs.getInt("morador_id"));
            moradores.add(morador);
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
    public Morador buscarMoradorPorUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM morador WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Morador morador = new Morador(rs.getInt("usuario_id"), rs.getInt("apartamento_id"));
                morador.setId(rs.getInt("id"));
                return morador;
            }
        }
        return null;
    }

}