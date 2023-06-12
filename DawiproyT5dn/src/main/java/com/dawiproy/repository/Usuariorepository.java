package com.dawiproy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dawiproy.entity.Enlace;
import com.dawiproy.entity.Usuario;

public interface Usuariorepository extends JpaRepository<Usuario, Integer> {
	
	@Query("select u from Usuario u where u.login=?1")
	public Usuario iniciarSesion(String vLogin);
	
	@Query("select e from RolEnlace re join re.enlace e where re.rol.codigo=?1")
	public List<Enlace> traerEnlacesDElUsuario(int codigoRol);
}
