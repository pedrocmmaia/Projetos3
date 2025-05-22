package dao;

import model.Administrador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    private Connection connection;

    public AdministradorDAO(Connection connection){
        this.connection = connection;
    }

    public  Integer cadastrarAdministrador(Administrador administrador) throws SQLException {
        String sql = "INSERT INTO administrador(usuario_id) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, administrador.getUsuarioId());

            stmt.executeUpdate();
            System.out.println("Administrador inserido com sucesso");

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                administrador.setId(rs.getInt(1));
            }
            return administrador.getUsuarioId();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Administrador buscarAdministradorPorId(int id) throws  SQLException {
        String sql = "SELECT * FROM administrador WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int usuarioId = rs.getInt("usuario_id");
                Administrador administrador =  new Administrador(usuarioId);
                administrador.setId(id);
                return administrador;
            }
        }
        return null;
    }

    public List<Administrador> listarAdministradores() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM administrador";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Administrador administrador = new Administrador(rs.getInt("usuario_id"));
                administrador.setId(rs.getInt("id"));
                administradores.add(administrador);
            }
        }
        return administradores;
    }

    public void deletarAdministrador(int id) throws SQLException {
        String sql = "DELETE FROM administrador WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Administrador deletado com sucesso");
        } catch (SQLException e) {
            System.out.println("Errp ap deletar administrar: " + e.getMessage());
        }
    }
}


