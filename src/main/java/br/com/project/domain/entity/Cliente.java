package br.com.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "nome", length = 100)
  @NotEmpty(message = "Campo nome é obrigatório")
  private String nome;

  @Column(name = "cpf", length = 11)
  @NotEmpty(message = "Campo CPF é obrigatório")
  @CPF(message = "Informe um CPF válido")
  private String cpf;

  @JsonIgnore
  @OneToMany(mappedBy = "cliente")
  private Set<Pedido> pedidos;
}
