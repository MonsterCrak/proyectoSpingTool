package com.dawiproy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawiproy.services.Empleadoservices;
import com.dawiproy.services.Productoservices;
import com.dawiproy.services.Sucursalservices;
import com.dawiproy.services.Usuarioservices;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private Empleadoservices serEmpleado;
	
	@Autowired
	private Sucursalservices serSucursal;
	
	@Autowired
	private Usuarioservices serUsuario;
	
	
	@RequestMapping("/lista")
	private String lista(Model model) {
		model.addAttribute("empleados", serEmpleado.listarTodos());
		return "Empleado";
	}
}



