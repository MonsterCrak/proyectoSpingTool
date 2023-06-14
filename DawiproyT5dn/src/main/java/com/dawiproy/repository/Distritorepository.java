package com.dawiproy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawiproy.entity.Distrito;

public interface Distritorepository extends JpaRepository<Distrito, Integer>{

	@Query("select d from Distrito d where d.prov.codigo=?1")
	public List<Distrito> listaTiposPorProvincias(Integer codDis);
	
}
