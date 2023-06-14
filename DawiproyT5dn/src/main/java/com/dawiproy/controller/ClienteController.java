package com.dawiproy.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		model.addAttribute("clientes", serCliente.listarTodos());
		model.addAttribute("provincia", serProvincia.listarProvincias());
		
		return "Cliente";
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod,
						 @RequestParam("nombre") String nom,
						 @RequestParam("paterno") String pat,
						 @RequestParam("materno") String mat,
						 @RequestParam("sexo") String sex,
						 @RequestParam("dni") Integer dn,
						 @RequestParam("fecha") String fec,
						 @RequestParam("celular") Integer cel,
						 @RequestParam("direccion") String dir,
						 @RequestParam("distritoscliente") Integer dis,
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
			dr.setCodigo(dis);
			c.setDistritosCliente(dr);
			
			if(cod==0) {
				serCliente.registrar(c);
				
				redirect.addFlashAttribute("MENSAJE","CLIENTE REGISTRADOR");
			}else {
				c.setCodigo(cod);
				serCliente.actualizar(c);
				redirect.addFlashAttribute("MENSAJE","CLIENTE ACTUALIZADO");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/cliente/lista";
	}
	
	
}
