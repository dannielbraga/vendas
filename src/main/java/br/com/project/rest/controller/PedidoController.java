package br.com.project.rest.controller;

import br.com.project.domain.entity.Pedido;
import br.com.project.rest.dto.PedidoDTO;
import br.com.project.service.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
  private final IPedidoService IPedidoService;

  @PostMapping
  public ResponseEntity<Pedido> salvar(@Valid @RequestBody PedidoDTO pedidoDTO){
    Pedido pedido = IPedidoService.salvar(pedidoDTO);
    return new ResponseEntity<>(pedido, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<Pedido> obterPorId(@PathVariable("id") Integer id){
    return new ResponseEntity<>(IPedidoService.obterPorId(id), HttpStatus.OK);
  }

}
