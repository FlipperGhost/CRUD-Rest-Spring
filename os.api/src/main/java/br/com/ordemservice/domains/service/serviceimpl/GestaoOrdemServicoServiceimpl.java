package br.com.ordemservice.domains.service.serviceimpl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ordemservice.api.model.Comentario;
import br.com.ordemservice.api.model.ComentarioInputModel;
import br.com.ordemservice.api.model.ComentarioModel;
import br.com.ordemservice.api.model.OrdemServicoInputModel;
import br.com.ordemservice.api.model.OrdemServicoModel;
import br.com.ordemservice.domains.exceptions.EntidadeNaoEncontradaException;
import br.com.ordemservice.domains.exceptions.NegocioException;
import br.com.ordemservice.domains.model.Cliente;
import br.com.ordemservice.domains.model.OrdemServico;
import br.com.ordemservice.domains.model.StatusOrdemServico;
import br.com.ordemservice.domains.repository.ClienteRepository;
import br.com.ordemservice.domains.repository.ComentarioRepository;
import br.com.ordemservice.domains.repository.OrdemServicoRepository;
import br.com.ordemservice.domains.service.GestaoOrdemServicoService;

@Service
public class GestaoOrdemServicoServiceimpl implements GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	public OrdemServicoModel criar(OrdemServicoInputModel ordemServicoInputModel) {
		OrdemServico ordemServico = toOrdemServico(ordemServicoInputModel);
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return toModel((ordemServicoRepository.save(ordemServico)));
	}

	

	@Override
	public List<OrdemServicoModel> listarOrdemServico() {

		return toCollectionModel(ordemServicoRepository.findAll());
	}

	@Override
	public ResponseEntity<OrdemServicoModel> bucarPorId(Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);

		if (ordemServico.isPresent()) {
			OrdemServicoModel model = toModel(ordemServico.get());
			return ResponseEntity.ok(model);
		} else
			return ResponseEntity.notFound().build();
	}

	@Override
	public ComentarioModel criarComentario(Long ordemServicoId, ComentarioInputModel comentarioInputModel) {
			OrdemServico ordemServico= ordemServicoRepository.findById(ordemServicoId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada"));
			
			Comentario comentario = toComentarioEntity(comentarioInputModel);
			comentario.setDataEnvio(OffsetDateTime.now());
			comentario.setOrdemServico(ordemServico);
			
			return toComentarioModel(comentarioRepository.save(comentario));
	}
	

	@Override
	public List<ComentarioModel> buscarComentario(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrda"));
		
		return toCollectionComentarioModel(ordemServico.getComentario());
	}

	@Override
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
		.orElseThrow(() -> new  EntidadeNaoEncontradaException("Ordem de serviço não encontrda"));
		ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
		ordemServico.setDataFinalizacao(OffsetDateTime.now());
		ordemServicoRepository.save(ordemServico);
		
	}
	
	@Override
	public void cancelar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new  EntidadeNaoEncontradaException("Ordem de serviço não encontrda"));
				ordemServico.setStatus(StatusOrdemServico.CANCELADA);
				ordemServicoRepository.save(ordemServico);
		
	}



	private ComentarioModel toComentarioModel(Comentario comentario) {
		
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionComentarioModel(List<Comentario> comentarios)
	{
		return comentarios.stream().map(comentario -> toComentarioModel(comentario)).collect(Collectors.toList());
	}



	private OrdemServicoModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoModel.class);
	}

	private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(ordemServico -> toModel(ordemServico)).collect(Collectors.toList());

	}
	private OrdemServico toOrdemServico(OrdemServicoInputModel ordemServicoInputModel) {
		return modelMapper.map(ordemServicoInputModel, OrdemServico.class);
	}

	private Comentario toComentarioEntity(ComentarioInputModel comentarioInputModel)
	{
		return modelMapper.map(comentarioInputModel, Comentario.class);
	}





	
	
}
