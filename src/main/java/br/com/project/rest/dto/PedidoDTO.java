package br.com.project.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
  @NotNull(message = "Informe o código do cliente")
  private Integer idCliente;

  @NotNull(message = "Campo valor total do pedido é obrigatório")
  private BigDecimal valorTotal;

  private List<ItemPedidoDTO> itensPedidoDTO;
}
