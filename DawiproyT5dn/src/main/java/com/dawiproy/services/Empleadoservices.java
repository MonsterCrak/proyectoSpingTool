package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dawiproy.entity.Empleado;
import com.dawiproy.repository.Empleadorepository;

public class Empleadoservices {

	@Autowired
	private Empleadorepository repo;
	
	public void registrar(Empleado bean) {
		repo.save(bean);
	}
	
	public void actualizar(Empleado bean) {
        repo.save(bean);
    }
	
	public Empleado buscarPorId(Integer cod) {
	     return repo.findById(cod).orElse(null);
	}
	
	public void eliminarPorID(Integer cod) {
        repo.deleteById(cod);
    }
	
	public List<Empleado> listarTodos(){
		return repo.findAll();
	}
	
}
