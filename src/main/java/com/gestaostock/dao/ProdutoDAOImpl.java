package com.gestaostock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM Produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = ConexaoFactory.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"),
                        rs.getInt("quatidade_estoque"));

                produtos.add(produto);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }

        return produtos;
    }

    @Override
    public void atualizar(Produto produto) {
        String sql = "UPDATE Produto SET nome = ', preco = ?, quantidade_estoque = ? WHERE id = ?";

        try (Connection conn = ConexaoFactory.getConexao()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidadeEstoque());
            ps.setInt(4, produto.getId());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum produto foi encontrado com Id " + produto.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Produto WHERE id = ?";

        try (Connection conn = ConexaoFactory.getConexao()) {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum produto foi encontrado com o Id " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }

}
