package br.com.project.service;

import br.com.project.domain.entity.Cliente;

import java.util.List;

public interface IClienteService {
    Cliente recuperarPorId(Integer id);

    Cliente salvar(Cliente cliente);

    Cliente atualizar(Integer id, Cliente cliente);

    void deletar(Integer id);

    List<Cliente> buscar(Cliente clienteFiltro);
}
