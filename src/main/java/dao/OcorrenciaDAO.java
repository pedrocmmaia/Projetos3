package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Apartamento;
import model.Bloco;

import model.Ocorrencia;
import model.Morador;

public class OcorrenciaDAO {
    private Connection connection;

    public OcorrenciaDAO(Connection connection) {
        this.connection = connection;
    }
    public Integer registrarOcorrencia(Ocorrencia ocorrencia) throws SQLException {
        String sql = "INSERT INTO ocorrencia (descricao, data_criacao, morador_id, status_ocorrencia, tipo_ocorrencia) VALUES (?, ?, ?,  ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ocorrencia.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(ocorrencia.getDataCriacao()));
            stmt.setInt(3, ocorrencia.getMorador().getId());
            stmt.setString(4, ocorrencia.getEstadoOcorrencia().name());
            stmt.setString(5, ocorrencia.getTipoOcorrencia().name());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
    public Ocorrencia buscarOcorrenciaPorId(int id) throws SQLException{
        String sql = """
                SELECT
                    o.id AS ocorrencia_id,
                    o.data_criacao AS data_criacao_ocorrencia,
                    o.tipo_ocorrencia AS tipo_ocorrencia,
                    o.descricao AS ocorrencia_descricao,
                    o.status_ocorrencia AS status_ocorrencia,
                    m.id AS morador_id,
                    u.nome AS nome_morador,
                    b.id AS bloco_id,
                    b.nome AS bloco_nome,
                    a.id AS apartamento_id,
                    a.andar AS apartamento_andar,
                    a.numero AS apartamento_numero
                FROM ocorrencia o
                LEFT JOIN morador m ON o.morador_id = m.id
                LEFT JOIN apartamento a ON m.apartamento_id = a.id
                LEFT JOIN bloco b ON a.bloco_id = b.id
                LEFT JOIN usuario u ON m.usuario_id = u.id
                WHERE o.id = ?
                """;
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Bloco bloco = new Bloco(
                        rs.getInt("bloco_id"),
                        rs.getString("bloco_nome")
                );

                Apartamento apartamento = new Apartamento(
                        rs.getInt("apartamento_id"),
                        rs.getInt("apartamento_numero"),
                        rs.getInt("apartamento_andar"),
                        bloco.getId()
                );

                Morador morador = new Morador(
                        rs.getInt("morador_id"),
                        rs.getString("nome_morador"),
                        null,
                        null,
                        null,
                        null,
                        apartamento
                );

                Ocorrencia ocorrencia = new Ocorrencia(
                        rs.getInt("ocorrencia_id"),
                        rs.getString("ocorrencia_descricao"),
                        rs.getTimestamp("data_criacao_ocorrencia").toLocalDateTime(),
                        Ocorrencia.TipoOcorrencia.fromString(rs.getString("tipo_ocorrencia")),
                        Ocorrencia.EstadoOcorrencia.fromString(rs.getString("status_ocorrencia")),
                        morador
                );

                ocorrencia.setId(rs.getInt("ocorrencia_id"));
                return ocorrencia;
            }
        }
        return null;

    }

    public List<Ocorrencia> listarTodasOcorrencias() throws SQLException {
        List<Ocorrencia> ocorrencias = new ArrayList<>();

        String sql = """
                SELECT
                    o.id AS ocorrencia_id,
                    o.data_criacao AS data_criacao_ocorrencia,
                    o.tipo_ocorrencia AS tipo_ocorrencia,
                    o.descricao AS ocorrencia_descricao,
                    o.status_ocorrencia AS status_ocorrencia,
                    m.id AS morador_id,
                    u.nome AS nome_morador,
                    b.id AS bloco_id,
                    b.nome AS bloco_nome,
                    a.id AS apartamento_id,
                    a.andar AS apartamento_andar,
                    a.numero AS apartamento_numero
                FROM ocorrencia o
                LEFT JOIN morador m ON o.morador_id = m.id
                LEFT JOIN apartamento a ON m.apartamento_id = a.id
                LEFT JOIN bloco b ON a.bloco_id = b.id
                LEFT JOIN usuario u ON m.usuario_id = u.id
                ORDER BY o.id;
                  """;

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {



                Bloco bloco = new Bloco(
                        rs.getInt("bloco_id"),
                        rs.getString("bloco_nome")
                );

                Apartamento apartamento = new Apartamento(
                        rs.getInt("apartamento_id"),
                        rs.getInt("apartamento_numero"),
                        rs.getInt("apartamento_andar"),
                        bloco.getId()
                );

                Morador morador = new Morador(
                        rs.getInt("morador_id"),
                        rs.getString("nome_morador"),
                        null,
                        null,
                        null,
                        null,
                        apartamento
                );

                Ocorrencia ocorrencia = new Ocorrencia(
                        rs.getInt("ocorrencia_id"),
                        rs.getString("ocorrencia_descricao"),
                        rs.getTimestamp("data_criacao_ocorrencia").toLocalDateTime(),
                        Ocorrencia.TipoOcorrencia.fromString(rs.getString("tipo_ocorrencia")),
                        Ocorrencia.EstadoOcorrencia.fromString(rs.getString("status_ocorrencia")),
                        morador
                        );
                ocorrencias.add(ocorrencia);
            }
        }
        return ocorrencias;
    }
    public void atualizarOcorrenciaDao(Ocorrencia ocorrencia) throws SQLException {
        String sql = "UPDATE ocorrencia SET descricao = ?, data_criacao = ?,  morador_id = ?, status_ocorrencia = ?, tipo_ocorrencia = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ocorrencia.getDescricao());
            stmt.setTimestamp(2, Timestamp.valueOf(ocorrencia.getDataCriacao()));
            stmt.setInt(3, ocorrencia.getMorador().getId());
            stmt.setString(4, ocorrencia.getEstadoOcorrencia().name());
            stmt.setString(5, ocorrencia.getTipoOcorrencia().name());
            stmt.setInt(6, ocorrencia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void deletarOcorrenciaDAO(int id) throws SQLException{
        String sql = "DELETE FROM ocorrencia WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
