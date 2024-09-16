package br.edu.ifpr.ordemservicoapi.controllers;

import br.edu.ifpr.ordemservicoapi.models.Comentario;
import br.edu.ifpr.ordemservicoapi.models.OrdemServico;
import br.edu.ifpr.ordemservicoapi.repositories.OrdemServicoRepository;
import br.edu.ifpr.ordemservicoapi.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private OrdemServicoRepository repository;

    @GetMapping("/{idCliente}/ordens")
    @ResponseStatus(HttpStatus.OK)
    public List<OrdemServico> ordemServicosPeloIdDoCliente(@PathVariable Long idCliente) {

        return repository.findByClienteId(idCliente);
    }
}
