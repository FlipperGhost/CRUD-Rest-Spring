package br.com.ordemservice.domains.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.ordemservice.api.model.ComentarioInputModel;
import br.com.ordemservice.api.model.ComentarioModel;
import br.com.ordemservice.api.model.OrdemServicoInputModel;
import br.com.ordemservice.api.model.OrdemServicoModel;

public interface GestaoOrdemServicoService {
	public OrdemServicoModel criar(OrdemServicoInputModel ordemServicoInputModel);

	public List<OrdemServicoModel> listarOrdemServico();

	public ResponseEntity<OrdemServicoModel> bucarPorId(Long ordemServicoId);
	
	public ComentarioModel criarComentario(Long ordemServicoId, ComentarioInputModel comentarioInputModel);
	
	public List<ComentarioModel> buscarComentario(Long ordemServicoId);

	public void finalizar(Long ordemServicoId);

	public void cancelar(Long ordemServicoId);
}
