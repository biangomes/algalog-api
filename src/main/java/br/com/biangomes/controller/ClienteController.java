package br.com.biangomes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.biangomes.domain.model.Cliente;
import br.com.biangomes.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {

	@PersistenceContext
	private EntityManager entityManager;		// injeta entitymanager

	private ClienteRepository repo;

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return repo.findAll();
	}

	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return repo.findById(clienteId)
			.map(cliente -> ResponseEntity.ok(cliente))
			.orElse(ResponseEntity.notFound().build());
	}
}
