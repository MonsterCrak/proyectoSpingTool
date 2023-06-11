package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Categoria;
import com.dawiproy.repository.Categoriarepository;

@Service
public class Categoriaservices {
	@Autowired
	private Categoriarepository repo;
	
	public List<Categoria> listarCategorias() {
		return repo.findAll();
	}
}
