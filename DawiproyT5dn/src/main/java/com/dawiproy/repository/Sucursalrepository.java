package com.dawiproy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawiproy.entity.Sucursal;

public interface Sucursalrepository extends JpaRepository<Sucursal, Integer>{

	public List<Sucursal> findByNombreStartingWith(String nom);
	
}
