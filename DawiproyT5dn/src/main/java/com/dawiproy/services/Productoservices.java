package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Producto;
import com.dawiproy.repository.Productorepository;

@Service
public class Productoservices {
	@Autowired
	private Productorepository repo;
	
	public void registrar(Producto bean) {
		repo.save(bean);
	}
	
	public void actualizar(Producto bean) {
		repo.save(bean);
	}
	
	public Producto buscarPorId(Integer cod) {
	     return repo.findById(cod).orElse(null);
	}
	
	public void eliminarPorID(Integer cod) {
		repo.deleteById(cod);
	}
	
	public List<Producto> listarTodos() {
		return repo.findAll();
	}
}
