package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Empleado;
import com.dawiproy.repository.Empleadorepository;

@Service
public class Empleadoservices {

	@Autowired
	private Empleadorepository repo;
	
	public void registrar(Empleado bean) {
		repo.save(bean);
	}
	
	public void actualizar(Empleado bean) {
        repo.save(bean);
    }
	
	public Empleado buscarPorId(String cod) {
	    return repo.findByCodigoEmpleado(cod);
	}

	
	public void eliminarPorID(String cod) {
        repo.deleteByCodigoEmpleado(cod);
    }
	
	
	public List<Empleado> listarTodos(){
		return repo.findAll();
	}
	
}
