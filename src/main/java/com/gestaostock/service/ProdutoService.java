package com.gestaostock.service;

import java.util.List;

import com.gestaostock.dao.ProdutoDAO;
import com.gestaostock.model.Produto;

public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void cadastrar(Produto produto) {
        validar(produto);
        produtoDAO.inserir(produto);
    }

    public Produto buscarPorId(int id) {
        validarId(id);
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }

    public void atualizar(Produto produto) {
        validar(produto);
        validarId(produto.getId());
        produtoDAO.atualizar(produto);
    }

    public void deletar(int id) {
        validarId(id);
        produtoDAO.deletar(id);
    }

    private void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new ValidacaoException("O campo nome do produto não pode estar vazio.");
        }

        if (produto.getNome().trim().length() < 2) {
            throw new ValidacaoException("O nome do produto deve conter pelomenos 2 carateres.");
        }

        if (produto.getPreco() < 0) {
            throw new ValidacaoException("O preco não pode ser Negativo.");
        }

        if (produto.getQuantidadeEstoque() < 0) {
            throw new ValidacaoException("A quantidade em estoque não pode ser negativa.");
        }

    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new ValidacaoException("ID invalido");
        }
    }

}
