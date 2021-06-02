package com.algaworks.crm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.algaworks.crm.model.Cliente;

@Service
public class ClienteDAO {
	
	
	@Autowired
	private JdbcTemplate jtm;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente getCliente(Long id) {
		
		String sql = "select * from cliente where id = ?";
		
		return jtm.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Cliente.class));
	}
	
	public ClienteRepository getRepository() {
		return clienteRepository;
	}

}
