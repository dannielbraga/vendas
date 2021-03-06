package br.com.project.service;

import br.com.project.domain.entity.Produto;

import java.util.List;

public interface IProdutoService {
    Produto recuperarPorId(Integer id);

    Produto salvar(Produto produto);

    Produto atualizar(Integer id, Produto produto);

    void deletar(Integer id);

    List<Produto> buscar(Produto produtoFiltro);
}
