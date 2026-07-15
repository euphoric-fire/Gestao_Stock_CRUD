package com.gestaostock.dao;

import java.util.List;

import com.gestaostock.model.Produto;

public interface ProdutoDAO {

    void inserir(Produto produto);

    Produto buscarPorId(int id);

    List<Produto> listarTodos();

    void atualizar(Produto produto);

    void deletar(int id);
}
