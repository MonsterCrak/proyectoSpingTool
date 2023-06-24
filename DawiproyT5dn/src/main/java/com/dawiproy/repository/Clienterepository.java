package com.dawiproy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawiproy.entity.Cliente;

public interface Clienterepository extends JpaRepository<Cliente, Integer>{

	public List<Cliente> findByPaternoStartingWith(String ape);
	
}
