package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Empleado;
import com.dawiproy.repository.Empleadorepository;

import jakarta.transaction.Transactional;

@Service
public class Empleadoservices {

	@Autowired
	private Empleadorepository repo;

	public void grabar(Empleado bean) {
		repo.save(bean);
	}

	public Empleado buscarPorId(String cod) {
		return repo.findByCodigoEmpleado(cod);
	}

	@Transactional
	public void eliminarPorID(String codigo) {
		repo.deleteByCodigoEmpleado(codigo);
	}

	public List<Empleado> listarTodos() {
		return repo.findAll();
	}

}
