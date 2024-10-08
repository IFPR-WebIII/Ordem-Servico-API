package br.edu.ifpr.ordemservicoapi.repositories;

import br.edu.ifpr.ordemservicoapi.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email); // Busca cliente pelo e-mail

}
