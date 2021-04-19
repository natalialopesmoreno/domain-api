package com.dominio.api.model.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

import com.dominio.api.model.Cliente;
import com.dominio.api.model.repository.ClienteRepository;
import com.dominio.api.model.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente)
	{
		try 
		{
			cliente = clienteService.salvar(cliente);
			return  ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping
	public List<Cliente> read()
	{
		return repository.findAll();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Long id)
	{
		try 
		{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
			
		}catch(Exception e) 
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id,
			@RequestBody Cliente cliente) {
		try {
			Optional<Cliente> clienteAtual = repository.findById(id);
			
			if (clienteAtual.isPresent()) {
				Cliente novoCliente = clienteAtual.get();
				BeanUtils.copyProperties(cliente, novoCliente, "id");
				novoCliente = clienteService.salvar(novoCliente);
				return ResponseEntity.ok(novoCliente);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}

	
	
	
	
	
	
	
}


