package com.clinica.entity;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class MedicamentoHasBoletaPK implements Serializable{
	
	@Column(name = "num_bol")
	private int numeroBoleta;
	@Column(name = "cod_med")
	private int codigoMedicamento;
	
	public int getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(int numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public int getCodigoMedicamento() {
		return codigoMedicamento;
	}
	public void setCodigoMedicamento(int codigoMedicamento) {
		this.codigoMedicamento = codigoMedicamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoMedicamento;
		result = prime * result + numeroBoleta;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicamentoHasBoletaPK other = (MedicamentoHasBoletaPK) obj;
		if (codigoMedicamento != other.codigoMedicamento)
			return false;
		if (numeroBoleta != other.numeroBoleta)
			return false;
		return true;
	}
	
}
