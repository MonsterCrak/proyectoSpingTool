package com.dawiproy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dawiproy.services.Productoservices;

@Controller
@RequestMapping(name = "/producto")
public class ProductoController {

	@Autowired
	private Productoservices serProducto;
	
	
	//@RequestMapping(name = "/lista")
	//private String lista(Model model) {
	//	return "Producto";
	//}
}



