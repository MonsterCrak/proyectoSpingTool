package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Provincia;
import com.dawiproy.repository.Provinciarepository;

@Service
public class Provinciaservices {
	
	@Autowired
	private Provinciarepository repo;
	
	public List<Provincia> listarProvincias(){
		return repo.findAll();
	}
}
