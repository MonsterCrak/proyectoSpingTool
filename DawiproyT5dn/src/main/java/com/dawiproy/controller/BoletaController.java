package com.dawiproy.controller;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawiproy.entity.Boleta;
import com.dawiproy.entity.Cliente;
import com.dawiproy.entity.Detalle;
import com.dawiproy.entity.Empleado;
import com.dawiproy.entity.Producto;
import com.dawiproy.entity.ProductoHasBoleta;
import com.dawiproy.entity.Usuario;
import com.dawiproy.services.Boletaservices;
import com.dawiproy.services.Clienteservices;
import com.dawiproy.services.Empleadoservices;
import com.dawiproy.services.Productoservices;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SessionAttributes({ "data", "datosUsuario", "codigoUsuario", "enlaces" })

@Controller
@RequestMapping("/boleta")
public class BoletaController {

	@Autowired
	private Clienteservices serCliente;

	@Autowired
	private Productoservices serProducto;

	@Autowired
	private Empleadoservices serEmpleado;

	@Autowired
	private Boletaservices serBoleta;

	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("clientes", serCliente.listarTodos());
		model.addAttribute("productos", serProducto.listarTodos());
		model.addAttribute("codigoBoleta", serBoleta.obtenerSiguienteNumeroBoleta());
		model.addAttribute("boletas", serBoleta.listarBoletas());
		return "boleta";
	}

	@RequestMapping("/listaJSON")
	@ResponseBody
	public List<Cliente> listaJSON(@RequestParam("apellido") String ape) {
		return serCliente.listaClientes(ape);
	}

	@RequestMapping("/listaProductoJSON")
	@ResponseBody
	public List<Producto> listaProductoJSON(@RequestParam("nombre") String nom) {
		return serProducto.buscarProductosPorNombre(nom);
	}

	@RequestMapping("/adicionar")
	@ResponseBody

	public List<Detalle> adicionar(@RequestParam("codigo") int cod, @RequestParam("descripcion") String des,
			@RequestParam("precio") double pre, @RequestParam("cantidad") int can, HttpSession session) {

		List<Detalle> lista = null;

		try {

			// declarar un arreglo de objetos de la clase Detalle

			// validar si existe el atruto de tipo session data
			if (session.getAttribute("data") == null) // no existe
				lista = new ArrayList<Detalle>();
			else
				// recuperar el atributo "data" y guardar su contenido en lsita

				lista = (List<Detalle>) session.getAttribute("data");

			// crear objeto de la clase Detalle
			Detalle det = new Detalle();
			// setear atrubutos del onjeto det con los valores de los parametros
			det.setCodigo(cod);
			det.setDescripcion(des);
			det.setPrecio(pre);
			det.setCantidad(can);

			lista.add(det);

			// crear atributo de tipo session "data"

			session.setAttribute("data", lista);

			// Agregar impresión en la consola
			System.out.println("Productos en sesión: " + lista);

		} catch (Exception e) {
			System.out.println("Se esta creando una excepcion");
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Detalle> eliminar(@RequestParam("codigo") int cod, HttpSession session) {
		List<Detalle> lista = (List<Detalle>) session.getAttribute("data");

		// Buscar el detalle a eliminar por su código
		Detalle detalleEliminar = null;
		for (Detalle det : lista) {
			if (det.getCodigo() == cod) {
				detalleEliminar = det;
				break;
			}
		}

		// Eliminar el detalle de la lista
		if (detalleEliminar != null) {
			lista.remove(detalleEliminar);

			// Actualizar la lista en la sesión
			session.setAttribute("data", lista);
		}

		return lista;
	}

	@RequestMapping("/actualizarCantidad")
	@ResponseBody
	public void actualizarCantidad(@RequestParam("codigo") int cod, @RequestParam("cantidad") int can,
			HttpSession session) {
		List<Detalle> lista = (List<Detalle>) session.getAttribute("data");

		if (lista != null) {
			for (Detalle det : lista) {
				if (det.getCodigo() == cod) {
					det.setCantidad(can);
					break;
				}
			}
		}
	}

	@RequestMapping("/listaDetalles")
	@ResponseBody
	public List<Detalle> listaDetalles(HttpSession session) {
		List<Detalle> lista = new ArrayList<>();

		try {
			if (session.getAttribute("data") != null) {
				lista = (List<Detalle>) session.getAttribute("data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping("/grabar")
	public String grabar(HttpSession session, RedirectAttributes redirectAttributes,
			@SessionAttribute("codigoUsuario") int codigoUsuario) {
		try {
			// Crear objeto de la clase Boleta
			Boleta boleta = new Boleta();
			// Setear los valores de la boleta definidos en el controlador
			boleta.setNumeroBoleta(1); // Reemplazar con el valor correspondiente
			boleta.setFechaEmision(new Date()); // Reemplazar con el valor correspondiente
			boleta.setMonto(1000.0); // Reemplazar con el valor correspondiente

			// Obtener el cliente (por ejemplo, desde un formulario o base de datos)
			Cliente cliente = serCliente.buscarPorId(1); // Reemplazar con tu lógica para obtener el cliente

			boleta.setCliente(cliente); // Asignar el cliente a la boleta
			boleta.setUsuario(new Usuario(codigoUsuario));

			// Obtener la lista de detalles de la sesión
			List<Detalle> detalles = (List<Detalle>) session.getAttribute("data");

			// Validar que la lista de detalles no sea nula o esté vacía
			if (detalles == null || detalles.isEmpty()) {
				throw new IllegalArgumentException("No se han agregado detalles a la boleta");
			}

			// Setear la lista de detalles en la boleta
			boleta.setListaProductoHasBoleta(convertirDetalles(detalles));

			// Llamar al servicio para registrar la boleta
			serBoleta.registrar(boleta);

			// Limpiar la lista de detalles de la sesión
			session.removeAttribute("data");

			return "redirect:/boleta/lista"; // Redireccionar a la página de lista de boletas
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "No se pudo efectuar el proceso. Inténtelo nuevamente.");
			return "redirect:/boleta/lista"; // Redireccionar a la página de lista de boletas
		}
	}

	private List<ProductoHasBoleta> convertirDetalles(List<Detalle> detalles) {
		List<ProductoHasBoleta> productosHasBoleta = new ArrayList<>();

		for (Detalle detalle : detalles) {
			ProductoHasBoleta productoHasBoleta = new ProductoHasBoleta();
			Producto producto = new Producto();

			// Setear los valores del productoHasBoleta con los valores del detalle
			productoHasBoleta.setPrecio(detalle.getPrecio());
			productoHasBoleta.setCantidad(detalle.getCantidad());

			// Setear los valores del producto con los valores del detalle
			producto.setCodigo(detalle.getCodigo());
			producto.setNombre(detalle.getDescripcion());

			productoHasBoleta.setProducto(producto);

			productosHasBoleta.add(productoHasBoleta);
		}

		return productosHasBoleta;
	}


	@RequestMapping("/boletass")
	@ResponseBody
	public List<Object[]> getBoletaDetailsByNumeroBoleta(@RequestParam("codigo") int numBoleta) {
		return serBoleta.getBoletaDetailsByNumeroBoleta(numBoleta);
	}

	@RequestMapping("/boletas")
	public void boletasporNumero(HttpServletResponse response) {
		try {
			// invocar al método listarTodos
			List<Object[]> lista = serBoleta.getBoletaDetailsByNumeroBoleta(1);
			// acceder al reporte "reporteMedicamento.jrxml"
			File file = ResourceUtils.getFile("classpath:BoletaReporte.jrxml");
			// crear objeto de la clase JasperReport y manipular el objeto file
			JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
			// origen de datos "manipular lista"
			JRBeanCollectionDataSource origen = new JRBeanCollectionDataSource(lista);
			// crear reporte
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, null, origen);
			// salida del reporte en formato PDF
			response.setContentType("application/pdf");
			//
			OutputStream salida = response.getOutputStream();
			// exportar a pdf
			JasperExportManager.exportReportToPdfStream(jasperPrint, salida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
