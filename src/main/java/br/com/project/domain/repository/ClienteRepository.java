package br.com.project.domain.repository;

import br.com.project.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
  List<Cliente> findByNomeLike(String nome);

  List<Cliente> findByNomeLikeOrIdOrderById(String nome, Integer id);

  Cliente findOneByCpf(String  cpf);

  boolean existsByNome(String nome);

  @Query(value = " select count(c.id) from Cliente c ")
  Integer retornarQtdeClientes();

  @Query(value = " select c from Cliente c where c.nome like :sobrenome ")
  Cliente recuperarClientePorSobrenome(@Param("sobrenome") String sobrenome);

  @Query(value = " delete from Cliente c where c.nome = :nome ")
  @Modifying
  void deletarClientePorNome(@Param("nome") String nome);

  @Query(value = " select c from Cliente c left join fetch c.pedidos where c.id = :idCliente ")
  Cliente findClienteFetchPedidos(@Param("idCliente") Integer idCliente);
}
