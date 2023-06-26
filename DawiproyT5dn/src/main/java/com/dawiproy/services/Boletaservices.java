package com.dawiproy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawiproy.entity.Boleta;
import com.dawiproy.entity.Categoria;
import com.dawiproy.entity.Empleado;
import com.dawiproy.entity.Producto;
import com.dawiproy.entity.ProductoHasBoleta;
import com.dawiproy.entity.ProductoHasBoletaPK;
import com.dawiproy.repository.Boletarepository;
import com.dawiproy.repository.Empleadorepository;
import com.dawiproy.repository.ProductoHasBoletaRepositorio;
import com.dawiproy.repository.Productorepository;

import jakarta.transaction.Transactional;

@Service
public class Boletaservices {

	@Autowired
	private Boletarepository serBoleta;

	@Autowired
	private ProductoHasBoletaRepositorio serPrdHasBoleta;

	public int obtenerSiguienteNumeroBoleta() {
		return serBoleta.obtenerSiguienteNumeroBoleta();
	}

	public List<Boleta> listarBoletas() {
		return serBoleta.findAll();
	}
	
	public List<Object[]> getBoletaDetailsByNumeroBoleta(int numBoleta) {
        return serBoleta.findBoletaDetailsByNumeroBoleta(numBoleta);
    }
	
	
	@Transactional
	public void registrar(Boleta bean) {
	    try {
	        // Validar los campos requeridos para la boleta
	    	  if (bean.getNumeroBoleta() == 0 || bean.getCliente() == null ||
	                  bean.getUsuario() == null || bean.getFechaEmision() == null || bean.getMonto() == 0) {
	              throw new IllegalArgumentException("Faltan campos requeridos para la boleta");
	          }
	        
	        // Grabar boleta "Cabecera---> tb_boleta
	        serBoleta.save(bean);
	        
	        // grabar BoletaHasProducto "Detalle---> "tb_producto_has_boleta"
	        // bucle para realizar recorrido sobre el atributo listaProductoHasBoleta del objeto "bean"
	        for (ProductoHasBoleta mhb : bean.getListaProductoHasBoleta()) {
	            // Validar los campos requeridos para ProductoHasBoleta
	            if (mhb.getBoleta() == null || mhb.getProducto() == null ||
	                    mhb.getPrecio() == 0 || mhb.getCantidad() == 0) {
	                throw new IllegalArgumentException("Faltan campos requeridos para Detalle boleta");
	            }
	            
	            // llave
	            ProductoHasBoletaPK pk = new ProductoHasBoletaPK();
	            // setear
	            pk.setNumeroBoleta(mhb.getBoleta().getNumeroBoleta());
	            pk.setcodigoProducto(mhb.getProducto().getCodigo());
	            // enviar pk al objeto mhb
	            mhb.setPk(pk);
	            // grabar mhb
	            serPrdHasBoleta.save(mhb);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	
	
	// anotación de spring
	//@Transactional
	//public void registrar(Boleta bean) {
	//	try {
			// Grabar boleta "Cabecera---> tb_boleta
	//		serBoleta.save(bean);
			// grabar BoletaHasProducto "Detalle---> "tb_producto_has_boleta"
			// bucle para realizar recorrido sobre el atributo listaProductoHasBoleta del
			// objeto "bean"
	//		for (ProductoHasBoleta mhb : bean.getListaProductoHasBoleta()) {
				// llave
	//			ProductoHasBoletaPK pk = new ProductoHasBoletaPK();
				// setear
	//			pk.setcodigoProducto(mhb.getProducto().getCodigo());
	//			pk.setNumeroBoleta(bean.getNumeroBoleta());
				// enviar pk al objeto mhb
	//			mhb.setPk(pk);
				// grabar mhb
	//			serPrdHasBoleta.save(mhb);
	//		}

	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	}

	//}

}
