package com.dawiproy.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_boleta")

public class Boleta implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_bol")
	private int numeroBoleta;
	@Temporal(TemporalType.DATE)
	@Column(name = "fec_emi_bol")
	private Date fechaEmision;
	@Column(name = "monto_bol")
	private double monto;
	
	//Relación MUCHOS a UNO "Usuario"
	@ManyToOne
	@JoinColumn(name = "cod_usu")
	private Usuario usuario;
	
	//Relación MUCHOS a UNO "Cliente"
	@ManyToOne
	@JoinColumn(name = "cod_cli")
	private Cliente cliente;
	
	//Relación UNO a MUCHOS "ConceptoHasBoleta"
	@OneToMany(mappedBy = "boleta")
	private List<ProductoHasBoleta> listaProductoHasBoleta;

	public int getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(int numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ProductoHasBoleta> getListaProductoHasBoleta() {
		return listaProductoHasBoleta;
	}

	public void setListaProductoHasBoleta(List<ProductoHasBoleta> listaProductoHasBoleta) {
		this.listaProductoHasBoleta = listaProductoHasBoleta;
	}

	
	
}
