package br.com.project.domain.entity;

import br.com.project.domain.enums.StatusPedido;
import br.com.project.validate.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @Column(name = "data_pedido")
  private LocalDate dataPedido;

  @Column(name = "valor_total", precision = 20, scale = 2)
  private BigDecimal valorTotal;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusPedido statusPedido;

  @OneToMany(mappedBy = "pedido")
  @NotEmptyList(message = "O Pedido não pode ser realizado sem itens")
  private List<ItemPedido> itensPedido;
}
