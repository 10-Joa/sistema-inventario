package sistema.inventario.producto;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import sistema.inventario.categoria.Categoria;
import sistema.inventario.categoria.CategoriaRepository;
import sistema.inventario.marca.MarcaController;

@Slf4j
@Controller
@Transactional
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private SessionFactory sessionFactory;

	@GetMapping("productos/nuevo")
	public String mostrarFormularioNuevoProducto(Model modelo) {
		List<Categoria> listaCategoria = categoriaRepository.findAll();

		modelo.addAttribute("producto", new Producto());
		modelo.addAttribute("listaCategoria", listaCategoria);

		return "producto_formulario";
	}

	@PostMapping("/productos/guardar")
	public String guardarProductos(Producto producto, HttpServletRequest request) {
		String[] detallesIDs = request.getParameterValues("detalleID");
		String[] detallesNombres = request.getParameterValues("detallesNombre");
		String[] detallesValores = request.getParameterValues("detallesValor");

		for (int i = 0; i < detallesNombres.length; i++) {
			if (detallesIDs != null && detallesIDs.length > 0) {
				log.info("cuando el detallesIDs no deberia ser nulo: "+ detallesIDs );
				producto.setDetalle(Integer.valueOf(detallesIDs[i]), detallesNombres[i], detallesValores[i]);
			}else {
				log.info("cuando el detallesIDs es nulo: "+ detallesIDs );
				producto.anadirDetalles(detallesNombres[i],detallesValores[i]);
			}
		}
		productoRepository.save(producto);
		return "redirect:/";
	}

	@GetMapping("productos")
	public String listarProducto(Model modelo) {
		List<Producto> listaProductos = productoRepository.findAll();

		modelo.addAttribute("listaProductos", listaProductos);

		return "productos";
	}

	@GetMapping("/productos/editar/{id}")
	public String mostrarFormularioEditarProducto(@PathVariable("id") Integer id, Model modelo) {
		List<Categoria> listaCategoria = categoriaRepository.findAll();

		Producto producto = productoRepository.findById(id).get();
		modelo.addAttribute("producto", producto);
		modelo.addAttribute("listaCategoria", listaCategoria);

		return "producto_formulario";
	}

	@GetMapping("/productos/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id, Model modelo) {
		productoRepository.deleteById(id);
		return "redirect:/productos";
	}
}
