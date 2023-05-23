package br.com.biangomes.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.biangomes.domain.model.Cliente;
import br.com.biangomes.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController("/clientes")
public class ClienteController {

	@PersistenceContext
	private EntityManager entityManager;		// injeta entitymanager

	private ClienteRepository repo;

	@GetMapping("")
	public List<Cliente> listar() {
		return repo.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return repo.findById(clienteId)
			.map(cliente -> ResponseEntity.ok(cliente))
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return repo.save(cliente);
	}
}
