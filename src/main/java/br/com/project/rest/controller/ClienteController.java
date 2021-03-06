package br.com.project.rest.controller;

import br.com.project.domain.entity.Cliente;
import br.com.project.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteRepository clienteRepository;

  @GetMapping("/{id}")
  public Cliente recuperarPorId(@PathVariable("id") Integer id){
    return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente salvar(@Valid @RequestBody Cliente cliente){
    return clienteRepository.save(cliente);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable("id") Integer id){
    clienteRepository
              .findById(id)
              .map(cliente -> {
                clienteRepository.delete(cliente);
                return cliente;
              })
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizar(@PathVariable("id") Integer id, @Valid @RequestBody Cliente cliente){
    clienteRepository
        .findById(id)
        .map(clienteExistente -> {
          cliente.setId(clienteExistente.getId());
          clienteRepository.save(cliente);
          return clienteExistente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
  }

  @GetMapping("/obterPorFiltro")
  public List<Cliente> findList(Cliente clienteFiltro){
    ExampleMatcher matcher = ExampleMatcher
                                      .matching()
                                      .withIgnoreCase()
                                      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    return clienteRepository.findAll(Example.of(clienteFiltro, matcher));
  }
}

