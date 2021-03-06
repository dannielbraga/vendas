package br.com.project.service.impl;

import br.com.project.domain.entity.Cliente;
import br.com.project.domain.repository.ClienteRepository;
import br.com.project.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente recuperarPorId(Integer id) {
        return this.clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Override
    public List<Cliente> recuperarTodos() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizar(Integer id, Cliente cliente) {
        return this.clienteRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Override
    public void deletar(Integer id) {
        this.clienteRepository
                .findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Override
    public List<Cliente> buscar(Cliente clienteFiltro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return clienteRepository.findAll(Example.of(clienteFiltro, matcher));
    }
}
