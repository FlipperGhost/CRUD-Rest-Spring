package br.com.ordemservice.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordemservice.api.model.ComentarioInputModel;
import br.com.ordemservice.api.model.ComentarioModel;
import br.com.ordemservice.domains.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;

	@GetMapping
	public List<ComentarioModel> buscarComentario(@PathVariable Long ordemServicoId) {
		return gestaoOrdemServicoService.buscarComentario(ordemServicoId);

	}

	@PostMapping
	ResponseEntity<ComentarioModel> criarComentario(@PathVariable Long ordemServicoId,
			@Valid  @RequestBody ComentarioInputModel comentarioInputModel) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(gestaoOrdemServicoService.criarComentario(ordemServicoId, comentarioInputModel));
	}

}
