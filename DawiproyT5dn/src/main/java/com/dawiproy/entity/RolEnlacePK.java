package com.dawiproy.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class RolEnlacePK implements Serializable{
 //para la llave primaria compuesta	
 private int idrol;
 private int idenlace;
 

public int getIdrol() {
	return idrol;
}
public void setIdrol(int idrol) {
	this.idrol = idrol;
}
public int getIdenlace() {
	return idenlace;
}
public void setIdenlace(int idenlace) {
	this.idenlace = idenlace;
}
//hashcode  -->genera un clave en función a los atributos de la clase actual
//equals --->valida la clave generada
@Override
public int hashCode() {
	return Objects.hash(idenlace, idrol);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	RolEnlacePK other = (RolEnlacePK) obj;
	return idenlace == other.idenlace && idrol == other.idrol;
}

	
}
