package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Distrito;
import com.dawiproy.repository.Distritorepository;

@Service
public class Distritoservices {

	@Autowired
	private Distritorepository repo;
	
	public List<Distrito> listarDistritos(){
		return repo.findAll();
	}
	
	//public List<Distrito> findAllTiposPorProvincias(Integer codLab){
	//	return repo.listaTiposPorProvincias(codLab);
	//}
}
