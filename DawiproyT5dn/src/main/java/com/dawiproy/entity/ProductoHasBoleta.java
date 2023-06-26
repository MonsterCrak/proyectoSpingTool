package com.dawiproy.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_producto_has_boleta")
public class ProductoHasBoleta implements Serializable {
	@EmbeddedId
	private ProductoHasBoletaPK pk;

	@ManyToOne
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
	private Producto producto;// ASOCI.

	// Relación MUCHOS a UNO "Boleta"
	@ManyToOne
	@JoinColumn(name = "num_bol", referencedColumnName = "num_bol", insertable = false, updatable = false)
	private Boleta boleta;// ASOCI.

	@Column(name = "precio")
	private double precio;

	@Column(name = "cantidad")
	private int cantidad;

	public ProductoHasBoletaPK getPk() {
		return pk;
	}

	public void setPk(ProductoHasBoletaPK pk) {
		this.pk = pk;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
