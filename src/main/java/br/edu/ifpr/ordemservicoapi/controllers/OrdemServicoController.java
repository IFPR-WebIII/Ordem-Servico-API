package br.edu.ifpr.ordemservicoapi.controllers;

import br.edu.ifpr.ordemservicoapi.models.Comentario;
import br.edu.ifpr.ordemservicoapi.models.OrdemServico;
import br.edu.ifpr.ordemservicoapi.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoService.listar();
    }

    @GetMapping("/{id}")
    public OrdemServico buscarPorId(@PathVariable Long id) {
        return ordemServicoService.buscarPorId(id).orElse(null);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Long id) {
        ordemServicoService.cancelar(id);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizar(@PathVariable Long id) {
        ordemServicoService.finalizar(id);
    }

    @PostMapping("/{ordemDeServicoId}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario adicionarComentario(@PathVariable Long ordemDeServicoId, @Validated @RequestBody Comentario comentario) {
        return ordemServicoService.adicionarComentario(ordemDeServicoId, comentario.getDescricao());
    }

}
