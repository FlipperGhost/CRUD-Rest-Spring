package br.com.ordemservice.domains.service.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ordemservice.domains.model.OrdemServico;
import br.com.ordemservice.domains.model.StatusOrdemServico;
import br.com.ordemservice.domains.repository.OrdemServicoRepository;
import br.com.ordemservice.domains.service.GestaoOrdemServicoService;

@Service
public class GestaoOrdemServicoServiceimpl implements GestaoOrdemServicoService {

	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	
	public OrdemServico criar(OrdemServico ordemServico)
	{
		
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		return ordemServicoRepository.save(ordemServico);
	}
	
	
}
