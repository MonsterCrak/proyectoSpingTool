package com.dawiproy.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cli")
	private Integer codigo;
	@Column(name = "nom_cli")
	private String nombre;
	@Column(name = "pat_cli")
	private String paterno;
	@Column(name = "mat_cli")
	private String materno;
	@Column(name = "sex_cli")
	private String sexo;
	@Column(name = "dni_cli")
	private Integer dni;
	@Column(name = "fec_nac_cli")
	private String fecha;
	@Column(name = "cel_cli")
	private Integer celular;
	@Column(name = "dir_cli")
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito DistritosCliente;

	
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

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Distrito getDistritosCliente() {
		return DistritosCliente;
	}

	public void setDistritosCliente(Distrito distritosCliente) {
		DistritosCliente = distritosCliente;
	}

	

	
	
}
