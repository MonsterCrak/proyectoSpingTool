package com.dawiproy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_sucursal")
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sucursal")
	private Integer codigo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "telefono")
	private String telefono;
	
	//relacion Muchos a Uno
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito disSucursal;//Asociación
	
	@OneToMany(mappedBy = "sucursal")
	@JsonIgnore
	private List<Empleado> listaparaEmpleados;

	
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Distrito getDis() {
		return disSucursal;
	}

	public void setDis(Distrito disSucursal) {
		this.disSucursal = disSucursal;
	}

	public Distrito getDisSucursal() {
		return disSucursal;
	}

	public void setDisSucursal(Distrito disSucursal) {
		this.disSucursal = disSucursal;
	}

	public List<Empleado> getListaparaEmpleados() {
		return listaparaEmpleados;
	}

	public void setListaparaEmpleados(List<Empleado> listaparaEmpleados) {
		this.listaparaEmpleados = listaparaEmpleados;
	}
	
	
	
	
	
}
