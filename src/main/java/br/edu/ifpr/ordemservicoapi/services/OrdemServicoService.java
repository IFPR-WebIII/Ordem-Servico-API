package br.edu.ifpr.ordemservicoapi.services;

import br.edu.ifpr.ordemservicoapi.models.Cliente;
import br.edu.ifpr.ordemservicoapi.models.Comentario;
import br.edu.ifpr.ordemservicoapi.models.OrdemServico;
import br.edu.ifpr.ordemservicoapi.models.StatusOrdemServico;
import br.edu.ifpr.ordemservicoapi.repositories.ClienteRepository;
import br.edu.ifpr.ordemservicoapi.repositories.ComentarioRepository;
import br.edu.ifpr.ordemservicoapi.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemDeServicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemDeServico) {

        // Verificar se o cliente já existe com base no email
        Optional<Cliente> clienteExistente = clienteRepository.findByEmail(ordemDeServico.getCliente().getEmail());

        if (clienteExistente.isPresent()) {
            // Se o cliente já existir, associá-lo à ordem de serviço
            ordemDeServico.setCliente(clienteExistente.get());
        } else {
            // Se o cliente não existir, salvá-lo no banco de dados
            clienteRepository.save(ordemDeServico.getCliente());
        }

        ordemDeServico.setDataAbertura(LocalDate.now());
        ordemDeServico.setStatus(StatusOrdemServico.ABERTA);
        return ordemDeServicoRepository.save(ordemDeServico);
    }

    public List<OrdemServico> listar() {
        return ordemDeServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Long id) {
        return ordemDeServicoRepository.findById(id);
    }

    public void cancelar(Long id) {
        Optional<OrdemServico> ordem = ordemDeServicoRepository.findById(id);
        if (ordem.isPresent()) {
            OrdemServico os = ordem.get();
            os.setStatus(StatusOrdemServico.CANCELADA);
            ordemDeServicoRepository.save(os);
        }
    }

    public void finalizar(Long id) {

        Optional<OrdemServico> ordem = ordemDeServicoRepository.findById(id);

        if (ordem.isPresent()) {
            OrdemServico os = ordem.get();
            os.setStatus(StatusOrdemServico.FINALIZADA);
            os.setDataFinalizacao(LocalDate.now());
            ordemDeServicoRepository.save(os);
        }

    }


    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        Optional<OrdemServico> ordemOptional = ordemDeServicoRepository.findById(ordemServicoId);

        if (!ordemOptional.isPresent()) {
            throw new RuntimeException("Ordem de serviço não encontrada");
        }

        OrdemServico ordemDeServico = ordemOptional.get();

        Comentario comentario = new Comentario();
        comentario.setDescricao(descricao);
        comentario.setDataEnvio(LocalDate.now());
        comentario.setOrdemServico(ordemDeServico);

        return comentarioRepository.save(comentario);

    }

}
