package com.gestaostock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gestaostock.model.Produto;

public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public void inserir(Produto produto) {
        String sql = "INSERT INTO Produto(nome, preco, quantidade_estoque) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoFactory.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidadeEstoque());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
    }

    @Override
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM Produto WHERE id = ?";

        try (Connection conn = ConexaoFactory.getConexao()) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"),
                            rs.getInt("quantidade_estoque"));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por Id", e);
        }
        return null;
    }

    @Override
    public List<Produto> listarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    @Override
    public void atualizar(Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

}
