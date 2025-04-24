package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.core.SqlCommand;

import model.Ocorrencia;
import model.Morador;
import config.DatabaseConfig;

public class OcorrenciaDAO {
    private Connection conexao;

    public OcorrenciaDAO() throws SQLException{
        this.conexao = DatabaseConfig.getConnection();
    }
    public void inserir(Ocorrencia ocorrencia) throws SQLException {
        String sql = "INSERT INTO ocorrencia (descricao, data_criacao, status, morador_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, ocorrencia.getDescricao());
        stmt.setTimestamp(2, Timestamp.valueOf(ocorrencia.getDataCriacao()));
        stmt.setString(3, ocorrencia.getEstadoOcorrencia().name().replace("_", " "));
        stmt.setInt(4, ocorrencia.getMorador().getId());
        stmt.executeUpdate();
    }
    public Ocorrencia buscarPorId(int id) throws SQLException{
        String sql = "SELECT * FROM ocorrencia WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()){
            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setId(rs.getInt("id"));
            ocorrencia.setDescricao(rs.getString("descricao"));
            ocorrencia.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
            ocorrencia.setEstadoOcorrencia(
                Ocorrencia.EstadoOcorrencia.fromString(rs.getString("status"))
            );

            Morador morador = new Morador();
            morador.setId(rs.getInt("morador_id"));
            ocorrencia.setMorador(morador);

            return ocorrencia;
        }
        return null;
    
    }

    public List<Ocorrencia> listarTodos() throws SQLException {
        String sql = "SELECT * FROM ocorrencia";
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Ocorrencia> lista = new ArrayList<>();
        while (rs.next()) {
            Ocorrencia ocorrencia = new Ocorrencia();
            ocorrencia.setId(rs.getInt("id"));
            ocorrencia.setDescricao(rs.getString("descricao"));
            ocorrencia.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime()); 
            ocorrencia.setEstadoOcorrencia(
                Ocorrencia.EstadoOcorrencia.fromString(rs.getString("status"))
            );
            Morador morador = new Morador();
            morador.setId(rs.getInt("morador_id"));
            ocorrencia.setMorador(morador);

            lista.add(ocorrencia);
        }

        return lista;

    }
    public void atualizar(Ocorrencia ocorrencia) throws SQLException {
        String sql = "UPDATE ocorrencia SET descricao = ?, data_criacao = ?, status = ?, morador_id = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, ocorrencia.getDescricao());
        stmt.setTimestamp(2, Timestamp.valueOf(ocorrencia.getDataCriacao()));
        stmt.setString(3, ocorrencia.getEstadoOcorrencia().name().replace("_", " "));
        stmt.setInt(4, ocorrencia.getMorador().getId());
        stmt.setInt(5, ocorrencia.getId());

        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException{
        String sql = "DELETE FROM ocorrencia WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
