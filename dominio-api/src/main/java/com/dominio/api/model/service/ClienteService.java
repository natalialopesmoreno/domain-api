package com.dominio.api.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dominio.api.model.Cliente;
import com.dominio.api.model.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	public Cliente salvar(Cliente cliente) 
	{
		if(cliente != null) 
		{
			return repository.save(cliente);
		}
		
		throw new IllegalArgumentException();
	}
	
	@GetMapping
	public List<Cliente> read()
	{
		return repository.findAll();
	}

}
