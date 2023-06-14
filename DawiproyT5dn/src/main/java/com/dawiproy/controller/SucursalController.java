package com.dawiproy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawiproy.entity.Distrito;
import com.dawiproy.entity.Sucursal;
import com.dawiproy.services.Distritoservices;
import com.dawiproy.services.Provinciaservices;
import com.dawiproy.services.Sucursalservices;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	private Provinciaservices serProvincia;

	@Autowired
	private Distritoservices serDistrito;

	@Autowired
	private Sucursalservices serSucursal;

	@RequestMapping("/lista")
	private String lista(Model model) {

		model.addAttribute("sucursal", serSucursal.listarTodos());
		model.addAttribute("provincias", serProvincia.listarProvincias());

		return "Sucursal";
	}

	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod, 
						@RequestParam("nombre") String nom,
						@RequestParam("telefono") String tel, 
						@RequestParam("distrito") Integer codDis) {

		try {
			// Crear objeto de la entidad Sucursal
			Sucursal m = new Sucursal();
			m.setNombre(nom);
			m.setTelefono(tel);

			// Crear objeto de la entidad Distrito
			Distrito dis = new Distrito();
			dis.setCodigo(codDis);
			m.setDistritosSucursal(dis);

			if (cod == 0) {
				serSucursal.registrar(m);
			} else {
				m.setCodigo(cod);
				serSucursal.actualizar(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sucursal/lista";
	}

	@RequestMapping("/buscar")
	@ResponseBody // Anotación para devolver un JSON en objeto
	public Sucursal buscarPorCodigo(@RequestParam("codigo") Integer cod) {
		return serSucursal.buscarPorId(cod);
	}

	@RequestMapping("/eliminar")
	public String eliminarPorCodigo(@RequestParam("codigoEliminar") Integer cod) {
	    serSucursal.eliminarPorID(cod);
	    return "redirect:/sucursal/lista";
	}


	// ruta (URL) para LISTAR UN arreglo de la entidad TipoMedicamento según
	// su codigo de laboratorio, dicha ruta retorna un JSON
	@RequestMapping("/listarDistritos")
	@ResponseBody // Anotación para devolver un JSON en objeto
	public List<Distrito> listarPorLaboratorio(@RequestParam("codigo") Integer cod) {
		return serDistrito.findAllTiposPorProvincias(cod);
	}
}
