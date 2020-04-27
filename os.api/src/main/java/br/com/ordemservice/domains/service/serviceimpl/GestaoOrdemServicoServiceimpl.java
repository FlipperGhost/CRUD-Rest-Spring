package br.com.ordemservice.domains.service.serviceimpl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ordemservice.domains.exceptions.NegocioException;
import br.com.ordemservice.domains.model.Cliente;
import br.com.ordemservice.domains.model.OrdemServico;
import br.com.ordemservice.domains.model.StatusOrdemServico;
import br.com.ordemservice.domains.repository.ClienteRepository;
import br.com.ordemservice.domains.repository.OrdemServicoRepository;
import br.com.ordemservice.domains.service.GestaoOrdemServicoService;




@Service
public class GestaoOrdemServicoServiceimpl implements GestaoOrdemServicoService {

	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico)
	{
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow( () ->  new NegocioException("Cliente não encontrado."));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}

	@Override
	public List<OrdemServico> listarOrdemServico() {
		
		return ordemServicoRepository.findAll();
	}

	@Override
	public ResponseEntity<OrdemServico> bucarPorId(Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		if(ordemServico.isPresent()) {
				return ResponseEntity.ok(ordemServico.get());
		}else return ResponseEntity.notFound().build();}
	
	
}
