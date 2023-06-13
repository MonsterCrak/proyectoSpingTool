package com.dawiproy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dawiproy.entity.Enlace;
import com.dawiproy.entity.Usuario;
import com.dawiproy.services.Usuarioservices;

@SessionAttributes({ "datosUsuario", "enlaces" })
@Controller
@RequestMapping("/validar")
public class ValidarController {
	@Autowired
	private Usuarioservices servicio;

	@RequestMapping("/usuario")
	public String index() {

		return "login";
	}

	@RequestMapping("/intranet")
	public String intranet(Authentication auth, Model model) {
		if (auth != null && auth.isAuthenticated()) {
			String nomUsuario = auth.getName();
			Usuario bean = servicio.loginUsuario(nomUsuario);
			List<Enlace> lista = servicio.enlacesDelUsuario(bean.getRol().getCodigo());
			model.addAttribute("datosUsuario", bean.getLogin() + " " + bean.getRol().getDescripcion());
			model.addAttribute("enlaces", lista);

			return "intranet";
		} else {
			// Si el usuario no está autenticado, redirige a la página de inicio de sesión
			return "redirect:/validar/usuario";
		}
	}

}
