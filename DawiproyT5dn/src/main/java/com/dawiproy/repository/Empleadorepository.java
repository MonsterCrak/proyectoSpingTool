package com.dawiproy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawiproy.entity.Empleado;

public interface Empleadorepository extends JpaRepository<Empleado, Integer>{
	
}
