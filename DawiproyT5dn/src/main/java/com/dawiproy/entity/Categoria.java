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
@Table(name = "tb_categoriaproducto")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Integer codigo;
	@Column(name = "nombre_categoria")
	private String nombre;
	
	//Relación Uno a Muchos
	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	private List<Producto> listaProducto;

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

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}
	
	
}