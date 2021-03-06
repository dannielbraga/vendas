package br.com.project.domain.repository;

import br.com.project.domain.entity.Cliente;
import br.com.project.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
  List<Pedido> findByCliente(Cliente cliente);

  @Query(" select p from Pedido p left join fetch p.itensPedido where p.id = :id ")
  Optional<Pedido> findByIdFetchItensPedido(@Param("id") Integer id);
}
