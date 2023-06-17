package com.dawiproy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawiproy.entity.Cliente;
import com.dawiproy.entity.Distrito;
import com.dawiproy.services.Clienteservices;
import com.dawiproy.services.Distritoservices;
import com.dawiproy.services.Provinciaservices;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private Clienteservices serCliente;
	
	@Autowired
	private Distritoservices serDistrito;
	
	@Autowired
	private Provinciaservices serProvincia;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("cliente", serCliente.listarTodos());
		model.addAttribute("provincias", serProvincia.listarProvincias());
		
		return "Cliente";
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod,
						 @RequestParam("nombre") String nom,
						 @RequestParam("paterno") String pat,
						 @RequestParam("materno") String mat,
						 @RequestParam("sexo") String sex,
						 @RequestParam("dni") String dn,
						 @RequestParam("fecha") String fec,
						 @RequestParam("celular") Integer cel,
						 @RequestParam("direccion") String dir,
						 @RequestParam("distritoscliente") Integer codDis,
						 RedirectAttributes redirect) {
		try {
			Cliente c = new Cliente();
			c.setNombre(nom);
			c.setPaterno(pat);
			c.setMaterno(mat);
			c.setSexo(sex);
			c.setDni(dn);
			c.setFecha(fec);
			c.setCelular(cel);
			c.setDireccion(dir);
			
			Distrito dr = new Distrito();
			dr.setCodigo(codDis);
			c.setDistritosCliente(dr);
			
			if(cod==0) {
				serCliente.registrar(c);
				
			}else {
				c.setCodigo(cod);
				serCliente.actualizar(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cliente/lista";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Cliente buscarPorCodigo(@RequestParam("codigo") Integer cod) {
		return serCliente.buscarPorId(cod);
	}
	
	@RequestMapping("/listarDistritos")
	@ResponseBody
	public List<Distrito> listarPorProvincia(@RequestParam("codigo") Integer cod){
		return serDistrito.findAllTiposPorProvincias(cod);
	}

	
	@RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> eliminarCliente(@PathVariable("codigo") Integer codigo) {
	    serCliente.eliminarPorID(codigo);
	    return ResponseEntity.ok("Cliente eliminado correctamente");
	}
	
}
