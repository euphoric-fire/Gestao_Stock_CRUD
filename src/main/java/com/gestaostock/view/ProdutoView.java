package com.gestaostock.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.gestaostock.model.Produto;

public class ProdutoView {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public int exibirMenu() throws IOException {
        System.out.println("\n=== GESTAO DE PRODUTOS ===");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Buscar produto por ID");
        System.out.println("3. Listar todos os produtos");
        System.out.println("4. Atualizar produto");
        System.out.println("5. Deletar produto");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
        return Integer.parseInt(br.readLine());
    }

    public Produto lerdadosNovProduto() throws IOException {
        System.out.println("Nome: ");
        String nome = br.readLine();
        System.out.println("Preço: ");
        double preco = Double.parseDouble(br.readLine());
        System.out.println("Quantidade em Estoque");
        int quantidade = Integer.parseInt(br.readLine());

        return new Produto(nome, preco, quantidade);

    }

    public int lerId() throws IOException {
        System.out.println("ID do produto: ");
        return Integer.parseInt(br.readLine());
    }

    public void mostrarProduto(Produto produto) {
        if (produto == null) {
            System.out.println("Produto não encontrado.");

        } else {
            System.out.println(produto);
        }
    }

    public void mostrarListaProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto foi cadastrado.");
            return;
        }

        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void mostrarMenssagem(String msg) {
        System.out.println(msg);
    }
}
