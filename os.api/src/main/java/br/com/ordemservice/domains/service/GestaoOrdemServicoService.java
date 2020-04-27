package br.com.ordemservice.domains.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.ordemservice.domains.model.OrdemServico;

public interface GestaoOrdemServicoService {
	public OrdemServico criar(OrdemServico ordemServico);

	public List<OrdemServico> listarOrdemServico();

	public ResponseEntity<OrdemServico> bucarPorId(Long ordemServicoId);
	
}
