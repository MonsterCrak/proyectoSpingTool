package com.dawiproy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_provincia")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_provincia")
	private Integer codigo;
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "prov")
	@JsonIgnore //Anotación para evitar generar JSON del arreglo de objetos listaMedicamento
	private List<Distrito> listaDistrito;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Distrito> getListaDistrito() {
		return listaDistrito;
	}

	public void setListaDistrito(List<Distrito> listaDistrito) {
		this.listaDistrito = listaDistrito;
	}
	
	
	
}
