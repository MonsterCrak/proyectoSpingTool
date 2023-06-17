package com.dawiproy.controller;

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

import com.dawiproy.entity.Categoria;
import com.dawiproy.entity.Producto;
import com.dawiproy.services.Categoriaservices;
import com.dawiproy.services.Productoservices;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private Categoriaservices serCategoria;
	
	@Autowired
	private Productoservices serProducto;
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("categorias", serCategoria.listarCategorias());
		model.addAttribute("producto", serProducto.listarTodos());
		
		return "Producto";
	}
	
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo") Integer cod,
						 @RequestParam("nombre") String nom,
						 @RequestParam("precio") double pre,
						 @RequestParam("categoria") Integer codCategoria,
						 RedirectAttributes redirect) {
		try {
			//Crear objeto de la entidad Producto
			Producto p = new Producto();
			p.setNombre(nom);
			p.setPrecio(pre);
			//Crear objeto de la entidad Categoria
			Categoria c = new Categoria();
			c.setCodigo(codCategoria);
			//Enviar objeto "c" dentro del objeto "p" invocar al mÃ©todo setCategoria
			p.setCategoria(c);
			
			if(cod==0) {
				serProducto.registrar(p);
			}else {
				p.setCodigo(cod);
				serProducto.actualizar(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/producto/lista";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Producto buscarPorCodigo(@RequestParam("codigo") Integer cod) {
		return serProducto.buscarPorId(cod);
	}

		
	@RequestMapping(value = "/eliminar/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> eliminarProducto(@PathVariable("codigo") Integer codigo) {
	    serProducto.eliminarPorID(codigo);
	    return ResponseEntity.ok("Producto eliminado correctamente");
	}

}



