package com.dawiproy.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawiproy.entity.Empleado;
import com.dawiproy.entity.Sucursal;
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
		model.addAttribute("sucursales", serSucursal.listarTodos());
		return "Empleado";
	}
	
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(
            @RequestParam("codigo") String cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("nacimiento") String nac,
            @RequestParam("puesto") String pue,
            @RequestParam("salario") double sal,
            @RequestParam("sucursal") Integer codSucu,
            RedirectAttributes redirect) {

        try {
            // Crear objeto de la entidad Empleado
            Empleado e = new Empleado();
            e.setCodigo(cod);
            e.setNombre(nom);
            e.setApellido(ape);
            e.setFecha_nacimiento(nac);
            e.setPuesto(pue);
            e.setSalario(sal);

            // Crear objeto de la entidad Sucursal
            Sucursal s = new Sucursal();
            s.setCodigo(codSucu);
            e.setSucursal(s);

            serEmpleado.registrar(e);

            // Enviar atributo
            redirect.addFlashAttribute("Mensaje", "Empleado registrado");
        } catch (Exception e) {
            redirect.addFlashAttribute("Mensaje", "Error en el registro");
            e.printStackTrace();
        }

        return "redirect:/empleado/lista";
    }
    
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public String actualizar(
	        @RequestParam("codigo") String cod,
	        @RequestParam("nombre") String nom,
	        @RequestParam("apellido") String ape,
	        @RequestParam("nacimiento") String nac,
	        @RequestParam("puesto") String pue,
	        @RequestParam("salario") double sal,
	        @RequestParam("sucursal") Integer codSucu,
	        RedirectAttributes redirect) {

	    try {
	        // Buscar el empleado existente por su código
	        Empleado empleadoExistente = serEmpleado.buscarPorId(cod);

	        if (empleadoExistente != null) {
	            // Si el empleado existe, actualiza sus datos
	            empleadoExistente.setNombre(nom);
	            empleadoExistente.setApellido(ape);
	            empleadoExistente.setFecha_nacimiento(nac);
	            empleadoExistente.setPuesto(pue);
	            empleadoExistente.setSalario(sal);

	            // Buscar la sucursal existente por su código
	            Sucursal sucursalExistente = serSucursal.buscarPorId(codSucu);
	            empleadoExistente.setSucursal(sucursalExistente);

	            serEmpleado.registrar(empleadoExistente);

	            redirect.addFlashAttribute("Mensaje", "Empleado actualizado");
	        } else {
	            // Si el empleado no existe, se puede mostrar un mensaje de error o redirigir a otra página
	            redirect.addFlashAttribute("Mensaje", "El empleado no existe");
	        }
	    } catch (Exception e) {
	        redirect.addFlashAttribute("Mensaje", "Error en la actualización");
	        e.printStackTrace();
	    }

	    return "redirect:/empleado/lista";
	}

	
	@RequestMapping("/buscar")
	@ResponseBody //Anotación para devolver un JSON en objeto
	public Empleado buscarPorCodigo(@RequestParam("codigo") String cod) {
		return serEmpleado.buscarPorId(cod);
	}
		
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public String eliminarPorCodigo(@RequestParam("codigoEliminar") String cod, RedirectAttributes redirect) {
	    Empleado empleado = serEmpleado.buscarPorId(cod);
	    if (empleado != null) {
	        serEmpleado.eliminarPorID(cod);
	        redirect.addFlashAttribute("Mensaje", "Empleado eliminado");
	    } else {
	        redirect.addFlashAttribute("Mensaje", "No se encontró el empleado");
	    }
	    return "redirect:/empleado/lista";
	}
	
	

	
	
}



