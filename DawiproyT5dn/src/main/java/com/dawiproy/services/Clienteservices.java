package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Cliente;
import com.dawiproy.repository.Clienterepository;

@Service
public class Clienteservices {
	@Autowired
	private Clienterepository repo;
	
	public void registrar(Cliente bean) {
		repo.save(bean);
	}
	
	public void actualizar(Cliente bean) {
        repo.save(bean);
    }
	
	public Cliente buscarPorId(Integer cod) {
	     return repo.findById(cod).orElse(null);
	}
	
	public void eliminarPorID(Integer cod) {
        repo.deleteById(cod);
    }
	
	public List<Cliente> listarTodos(){
		return repo.findAll();
	}
	
	public List<Cliente> listaClientes(String ape){
		return repo.findByPaternoStartingWith(ape);
	}
}
