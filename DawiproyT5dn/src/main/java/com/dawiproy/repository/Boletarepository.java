package com.dawiproy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawiproy.entity.Boleta;

public interface Boletarepository extends JpaRepository<Boleta, Integer>{
	
	@Query(value = "SELECT COUNT(*) AS cantidad_num_bol FROM tb_boleta", nativeQuery = true)
    int obtenerSiguienteNumeroBoleta();
	
}
