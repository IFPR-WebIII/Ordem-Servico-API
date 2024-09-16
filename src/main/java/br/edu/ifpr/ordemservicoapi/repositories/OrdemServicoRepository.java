package br.edu.ifpr.ordemservicoapi.repositories;

import br.edu.ifpr.ordemservicoapi.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    public List<OrdemServico> findByClienteId(Long id);

}
