package dao;

import model.Administrador;
import model.Usuario;

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
            stmt.setInt(1, administrador.getId());

            stmt.executeUpdate();
            System.out.println("Administrador inserido com sucesso");

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                administrador.setId(rs.getInt(1));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Administrador buscarAdministradorPorId(int id) throws  SQLException {
        String sql ="""
                SELECT
                    a.id AS administrador_id,
            
                    u.id AS usuario_id,
                    u.nome AS usuario_nome,
                    u.email AS usuario_email,
                    u.telefone AS usuario_telefone,
                    u.tipo_usuario AS usuario_tipo
            
                 FROM administrador a
                 JOIN usuario u ON a.usuario_id = u.id
                 WHERE u.id = ?
             """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Administrador administrador = new Administrador(
                        rs.getInt("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_email"),
                        null, //Preservar a senha
                        rs.getString("usuario_telefone"),
                        Usuario.TipoUsuario.valueOf(rs.getString("usuario_tipo"))
                );
                administrador.setId(rs.getInt("administrador_id"));
                return administrador;
            }
        }
        return null;
    }

    public List<Administrador> listarAdministradores() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();
        String sql = """
                SELECT
                    a.id AS administrador_id,
            
                    u.id AS usuario_id,
                    u.nome AS usuario_nome,
                    u.email AS usuario_email,
                    u.telefone AS usuario_telefone,
                    u.tipo_usuario AS usuario_tipo
            
                 FROM administrador a
                 JOIN usuario u ON a.usuario_id = u.id
             """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Administrador administrador= new Administrador(
                        rs.getInt("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_email"),
                        null, //Preservar a senha
                        rs.getString("usuario_telefone"),
                        Usuario.TipoUsuario.valueOf(rs.getString("usuario_tipo"))
                );
                administrador.setId(rs.getInt("administrador_id"));
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


