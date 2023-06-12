package com.dawiproy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawiproy.entity.Distrito;

public interface Distritorepository extends JpaRepository<Distrito, Integer>{

	//Select * from tb_tipo_Medicamento where cod_lab=1
	//@Query("select tm from TipoMedicamento tm where tm.laboratorio.codigo=?1")
	public List<Distrito> listaTiposPorProvincias(Integer codLab);
	
}
