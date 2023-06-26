package com.dawiproy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawiproy.entity.Boleta;

public interface Boletarepository extends JpaRepository<Boleta, Integer>{
	
	@Query(value = "SELECT COUNT(*) AS cantidad_num_bol FROM tb_boleta", nativeQuery = true)
    int obtenerSiguienteNumeroBoleta();
	
	@Query("SELECT b.fechaEmision AS fechaEmision, " +
            "b.monto AS monto, " +
            "p.nombre AS nombreProducto, " +
            "d.precio AS precio, " +
            "d.cantidad AS cantidad, " +
            "c.nombre AS nombreCliente " +
            "FROM Boleta b " +
            "INNER JOIN b.listaProductoHasBoleta d " +
            "INNER JOIN d.producto p " +
            "INNER JOIN b.cliente c " +
            "WHERE b.numeroBoleta = :codigo")
    List<Object[]> findBoletaDetailsByNumeroBoleta(@Param("codigo") int codigo);
	
}
