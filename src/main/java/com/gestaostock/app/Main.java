package com.gestaostock.app;

import java.io.IOException;
import java.util.List;

import com.gestaostock.controller.ProdutoController;
import com.gestaostock.dao.ProdutoDAO;
import com.gestaostock.dao.ProdutoDAOImpl;
import com.gestaostock.model.Produto;
import com.gestaostock.service.ProdutoService;
import com.gestaostock.service.ValidacaoException;
import com.gestaostock.view.ProdutoView;

public class Main {
    public static void main(String[] args) throws IOException {

        ProdutoDAO produtoDAO = new ProdutoDAOImpl();
        ProdutoService produtoService = new ProdutoService(produtoDAO);
        ProdutoController controller = new ProdutoController(produtoService);
        ProdutoView produtoView = new ProdutoView();

        boolean funcionando = true;

        while (funcionando) {
            int opcao = produtoView.exibirMenu();

            switch (opcao) {
                case 1:
                    try {
                        Produto novo = produtoView.lerdadosNovProduto();
                        controller.cadastrar(novo);
                        produtoView.mostrarMenssagem("Produto cadastrado com sucesso.");
                    } catch (ValidacaoException e) {
                        produtoView.mostrarMenssagem("Erro de validação: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        int id = produtoView.lerId();
                        Produto produto = controller.buscarPorId(id);
                        produtoView.mostrarProduto(produto);

                    } catch (ValidacaoException e) {
                        produtoView.mostrarMenssagem("Erro de validação: " + e.getMessage());
                    }
                    break;

                case 3:
                    List<Produto> produtos = controller.listarTodos();
                    produtoView.mostrarListaProdutos(produtos);
                    break;
                case 4:
                    try {
                        int id = produtoView.lerId();
                        Produto exixtente = controller.buscarPorId(id);
                        if (exixtente == null) {
                            produtoView.mostrarMenssagem("Produto não encontrado.");
                        } else {
                            Produto atualizado = produtoView.lerdadosNovProduto();
                            atualizado.setId(id);
                            controller.atualizar(atualizado);
                            produtoView.mostrarMenssagem("Produto atualizado com sucesso.");
                        }

                    } catch (ValidacaoException e) {
                        produtoView.mostrarMenssagem("Erro de validação: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        int id = produtoView.lerId();
                        controller.deletar(id);
                        produtoView.mostrarMenssagem("Porduto deletado com sucesso.");

                    } catch (ValidacaoException e) {
                        produtoView.mostrarMenssagem("Erro de validação: " + e.getMessage());
                    }
                    break;

                case 0:
                    funcionando = false;
                    produtoView.mostrarMenssagem("Encerando até a proxima.");
                    break;

                default:
                    produtoView.mostrarMenssagem("Opção invalida.");
                    break;
            }

        }
    }
}