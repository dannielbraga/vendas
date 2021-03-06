package br.com.project.rest.controller;

import br.com.project.domain.entity.Cliente;
import br.com.project.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
    private final IClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> recuperarPorId(@PathVariable("id") Integer id){
        Cliente cliente = this.clienteService.recuperarPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> recuperarTodos(){
        List<Cliente> clientes = this.clienteService.recuperarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(this.clienteService.salvar(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Integer id){
        this.clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Cliente> atualizar(@PathVariable("id") Integer id, @Valid @RequestBody Cliente cliente){
        return new ResponseEntity<>(this.clienteService.atualizar(id, cliente), HttpStatus.OK);
    }

    @GetMapping("/obterPorFiltro")
    public ResponseEntity<List<Cliente>> findList(Cliente clienteFiltro){
        List<Cliente> clientes = this.clienteService.buscar(clienteFiltro);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}

