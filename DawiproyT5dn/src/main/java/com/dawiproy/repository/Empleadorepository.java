package com.dawiproy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawiproy.entity.Empleado;

public interface Empleadorepository extends JpaRepository<Empleado, Integer>{
	
	@Query("SELECT e FROM Empleado e where e.codigo = :cod")
	public Empleado findByCodigoEmpleado(@Param("cod") String cod);
	
	@Query("DELETE FROM Empleado e WHERE e.codigo = :cod")
	void deleteByCodigoEmpleado(@Param("cod") String cod);

}
