package br.com.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id")
  private Integer id;

  @NotEmpty(message = "Campo Descrição é obrigatório")
  @Column(name = "descricao")
  private String descricao;

  @Column(name = "preco_unitario", precision = 20, scale = 2)
  @NotNull(message = "Campo Preço é obrigatório")
  private BigDecimal preco;
}
