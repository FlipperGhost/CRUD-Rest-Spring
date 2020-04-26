package br.com.ordemservice.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordemservice.domains.model.Cliente;
import br.com.ordemservice.domains.service.ClienteService;



@RestController
@RequestMapping("/Clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {

		return clienteService.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		var cliente = clienteService.findById(id);

		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}
	}

	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente) {
		var objCliente = clienteService.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(objCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		var objCliente = clienteService.update(id, cliente);
		if (objCliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(objCliente);

		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		var objCliente = clienteService.deleteById(id);
		if (objCliente) {
			return ResponseEntity.noContent().build();

		} else {
			return ResponseEntity.notFound().build();

		}
	}

}
