package com.algaworks.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.crm.model.Cliente;
import com.algaworks.crm.repository.ClienteDAO;

@RestController
public class ClienteController {
	
	
	@Autowired
	private ClienteDAO clienteDAO; 
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteDAO.getRepository().findAll();
	}
	
	@GetMapping("/cliente/{id}")
	public Cliente cliente(@PathVariable Long id) {
		return clienteDAO.getCliente(id);
	}
	
	//tentar mudar a url
	@PostMapping("/cadastro/{nome}")
	@ResponseStatus (HttpStatus.CREATED)
	public Cliente adicionar(@PathVariable String nome) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		return clienteDAO.getRepository().save(cliente);
	}

}
