package br.edu.ifpr.ordemservicoapi.repositories;

import br.edu.ifpr.ordemservicoapi.models.Comentario;
import br.edu.ifpr.ordemservicoapi.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
