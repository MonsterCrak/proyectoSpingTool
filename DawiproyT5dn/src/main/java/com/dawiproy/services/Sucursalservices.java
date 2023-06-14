package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Sucursal;
import com.dawiproy.repository.Sucursalrepository;

@Service
public class Sucursalservices {
	@Autowired
	private Sucursalrepository repo;
	
	public void registrar(Sucursal s) {
		repo.save(s);
	}
	
	public void actualizar(Sucursal s) {
		repo.save(s);
	}
	
	public Sucursal buscarPorId(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	
	public void eliminarPorID(Integer cod) {
		repo.deleteById(cod);
	}
	
	public List<Sucursal> listarTodos(){
		return repo.findAll();
	}
	
	public List<Sucursal> listarSucursalesPorNombre(String nom){
		return repo.findByNombreStartingWith(nom);
	}
}
