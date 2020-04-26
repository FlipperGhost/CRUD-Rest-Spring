package br.com.ordemservice.domains.service;

import java.util.List;

import br.com.ordemservice.domains.model.Cliente;



public interface ClienteService {


	public Cliente findById(final Long id);

	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);

	public Cliente update(Long id, Cliente cliente);

	public boolean deleteById(Long id);
}
