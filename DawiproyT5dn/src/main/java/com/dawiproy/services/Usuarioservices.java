package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Enlace;
import com.dawiproy.entity.Usuario;
import com.dawiproy.repository.Usuariorepository;

@Service
public class Usuarioservices {

	@Autowired
	private Usuariorepository repo;
	
	public Usuario loginUsuario(String vLogin){
		return repo.iniciarSesion(vLogin);
	}
	public List<Enlace> enlacesDelUsuario(int rol){
		return repo.traerEnlacesDElUsuario(rol);
	}
	
}
