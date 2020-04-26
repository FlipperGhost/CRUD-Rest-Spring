package br.com.ordemservice.domains.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ordemservice.domains.exceptions.NegocioException;
import br.com.ordemservice.domains.model.Cliente;
import br.com.ordemservice.domains.repository.ClienteRepository;
import br.com.ordemservice.domains.service.ClienteService;



@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);

	}

	@Override
	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	@Override
	public Cliente save(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já  existe um cliente cadastrado com este e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}

	

	@Override
	public Cliente update(Long id, Cliente cliente) {
		if (clienteRepository.existsById(id)) {

			cliente.setId(id);
			return clienteRepository.save(cliente);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;
		} else
			return false;

	}
}
