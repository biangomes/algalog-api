package br.com.biangomes.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biangomes.domain.model.Cliente;
import br.com.biangomes.domain.repository.ClienteRepository;

@RestController
public class ClienteController {

	@PersistenceContext
	private EntityManager entityManager;		// injeta entitymanager

	@Autowired
	private ClienteRepository repo;

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return entityManager.createQuery("from Cliente", Cliente.class)
			.getResultList();
	}
}
