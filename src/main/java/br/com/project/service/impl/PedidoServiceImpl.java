package br.com.project.service.impl;

import br.com.project.domain.entity.Cliente;
import br.com.project.domain.entity.ItemPedido;
import br.com.project.domain.entity.Pedido;
import br.com.project.domain.entity.Produto;
import br.com.project.domain.enums.StatusPedido;
import br.com.project.domain.repository.ClienteRepository;
import br.com.project.domain.repository.ItemPedidoRepository;
import br.com.project.domain.repository.PedidoRepository;
import br.com.project.domain.repository.ProdutoRepository;
import br.com.project.exception.RegraNegocioException;
import br.com.project.rest.dto.ItemPedidoDTO;
import br.com.project.rest.dto.PedidoDTO;
import br.com.project.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;
  private final ProdutoRepository produtoRepository;
  private final ItemPedidoRepository itemPedidoRepository;

  @Override
  @Transactional
  public Pedido salvar(PedidoDTO pedidoDTO) {
    Integer idCliente = pedidoDTO.getIdCliente();
    Cliente cliente = clienteRepository
                      .findById(idCliente)
                      .orElseThrow(() -> new RegraNegocioException("Código de Cliente inválido: " + idCliente));

    Pedido pedido = new Pedido();
    pedido.setValorTotal(pedidoDTO.getValorTotal());
    pedido.setDataPedido(LocalDate.now());
    pedido.setCliente(cliente);
    pedido.setStatusPedido(StatusPedido.REALIZADO);

    List<ItemPedido> itensPedido = converterItens(pedido, pedidoDTO.getItensPedidoDTO());
    pedidoRepository.save(pedido);
    itemPedidoRepository.saveAll(itensPedido);
    pedido.setItensPedido(itensPedido);

    return pedido;
  }

  private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itensPedidoDTO){
    if(itensPedidoDTO.isEmpty()){
      throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
    }

    return itensPedidoDTO
        .stream()
        .map(itemPedidoDTO -> {
          Integer idProduto = itemPedidoDTO.getIdProduto();
          Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de Produto inválido: " + idProduto));

          ItemPedido itemPedido = new ItemPedido();
          itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
          itemPedido.setPedido(pedido);
          itemPedido.setProduto(produto);
          return itemPedido;
        }).collect(Collectors.toList());
  }

  @Override
  public Pedido obterPorId(Integer id) {
    return pedidoRepository
        .findByIdFetchItensPedido(id)
        .orElseThrow(() -> new RegraNegocioException("Não foi possível encontrar o pedido: " + id));
  }
}
