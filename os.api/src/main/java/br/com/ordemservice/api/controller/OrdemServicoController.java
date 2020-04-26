package br.com.ordemservice.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordemservice.domains.model.OrdemServico;
import br.com.ordemservice.domains.service.serviceimpl.GestaoOrdemServicoServiceimpl;

@RestController
@RequestMapping("/ordens/servico")
public class OrdemServicoController {

	
	@Autowired
	GestaoOrdemServicoServiceimpl gestaoOrdemServico;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrdemServico>  criar(@Valid @RequestBody OrdemServico ordemServico)
	{
	var ObjOrdemServico = gestaoOrdemServico.criar(ordemServico);
	return ResponseEntity.status(HttpStatus.CREATED).body(ObjOrdemServico);
	}
}
