package br.com.project.service;

import br.com.project.domain.entity.Pedido;
import br.com.project.rest.dto.PedidoDTO;

public interface IPedidoService {
  Pedido salvar(PedidoDTO pedidoDTO);

  Pedido obterPorId(Integer id);
}
