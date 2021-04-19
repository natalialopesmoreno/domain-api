package com.dominio.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominio.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	

}
