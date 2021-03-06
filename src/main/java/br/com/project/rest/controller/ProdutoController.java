package br.com.project.rest.controller;

import br.com.project.domain.entity.Produto;
import br.com.project.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {
  private final ProdutoService produtoService;

  @GetMapping("{id}")
  public ResponseEntity<Produto> recuperarPorId(@PathVariable("id") Integer id){
      Produto produto = this.produtoService.recuperarPorId(id);
      return new ResponseEntity<>(produto, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto){
      return new ResponseEntity<>(this.produtoService.salvar(produto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Produto> atualizar(@PathVariable("id") Integer id, @Valid @RequestBody Produto produto){
      return new ResponseEntity<>(this.produtoService.atualizar(id, produto), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deletar(@PathVariable("id") Integer id){
      this.produtoService.deletar(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<List<Produto>> find(Produto produtoFiltro){
      List<Produto> produtos = this.produtoService.buscar(produtoFiltro);
      return new ResponseEntity<>(produtos, HttpStatus.OK);
  }
}
