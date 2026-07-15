package com.gestaostock.controller;

import java.util.List;

import com.gestaostock.model.Produto;
import com.gestaostock.service.ProdutoService;

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public void cadastrar(Produto produto) {
        produtoService.cadastrar(produto);
    }

    public Produto buscarPorId(int id) {
        return produtoService.buscarPorId(id);
    }

    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    public void atualizar(Produto produto) {
        produtoService.atualizar(produto);
    }

    public void deletar(int id) {
        produtoService.deletar(id);
    }

}
