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
@Table(name ="tb_distrito")
public class Distrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_provincia")
	private Integer codigo;
	@Column(name = "nombre")
	private String nombre;
	
	//relacion Muchos a Uno
	@ManyToOne
	@JoinColumn(name = "id_provincia")
	private Provincia prov;//Asociación
	
	@OneToMany(mappedBy = "disSucursal")
	@JsonIgnore //Anotación para evitar generar JSON del arreglo de objetos listaMedicamento
	private List<Sucursal> listarparaSucursal;

	@OneToMany(mappedBy = "disCliente")
	@JsonIgnore
	private List<Distrito> listarparaCliente;


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

	public Provincia getProv() {
		return prov;
	}

	public void setProv(Provincia prov) {
		this.prov = prov;
	}
	
	
}
