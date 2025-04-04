package dao;

import model.Morador;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoradorDAO{
    private Connection conexao;

    public MoradorDAO(Connection conexao){
        this.conexao = conexao;
    }

    public void inserir(Morador morador) throws SQLException{
        String sql = "INSERT Into usuario (nome, email, senha, telefone, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, morador.getNome());
            statement.setString(2, morador.getEmail());
            statement.setString(3, morador.getSenha());
            statement.setString(4, morador.getTelefone());
            statement.setString(5, Usuario.Tipo.Morador.name());
            
            statement.executeUpdate();
            System.out.println("Morador cadastrado com sucesso!");

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                morador.setId(rs.getInt(1));
            }
        }
    }

    public Morador buscarPorId(int id) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id = ? AND tipo = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.setString(2, Usuario.Tipo.Morador.name());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return new Morador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("telefone")
                    );
            }
        }
        return null;
    }

    public List<Morador> buscarTodos() throws SQLException{
    List<Morador> moradores = new ArrayList<>();
    String sql = "SELECT * FROM usuario WHERE tipo_usuario = ?";

    try(PreparedStatement statement = conexao.prepareStatement(sql)){
        statement.setString(1, Usuario.Tipo.Morador.name());
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            moradores.add(new Morador(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("telefone")
                    ));
        }
    }
    return moradores;
    }

public void atualizar(Morador morador) throws SQLException{
    String sql = "UPDATE usuario SET nome = ?, senha = ?, telefone = ? WHERE id = ? AND tipo_usuario = ?";

    try(PreparedStatement statement = conexao.prepareStatement(sql)){
        statement.setString(1, morador.getNome());
        statement.setString(2, morador.getEmail());
        statement.setString(3, morador.getSenha());
        statement.setString(4, morador.getTelefone());
        statement.setInt(5, morador.getId());
        statement.setString(6, Usuario.Tipo.Morador.name());

        statement.executeUpdate();
        System.out.println("Morador atualizado com sucesso!");
    }
}

public void deletar(int id) throws SQLException{
    String sql = "DELETE FROM usuario WHERE id = ? AND tipo_usuario = ?";

    try(PreparedStatement statement = conexao.prepareStatement(sql)){
        statement.setInt(1, id);
        statement.setString(2, Usuario.Tipo.Morador.name());
        statement.executeUpdate();
        System.out.println("Morador removido com sucesso!");
    }
}
}