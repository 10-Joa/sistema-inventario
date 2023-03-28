package sistema.inventario.marca;

import java.util.List;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;
import sistema.inventario.categoria.Categoria;
import sistema.inventario.categoria.CategoriaRepository;

@Slf4j
@Controller
@Transactional
public class MarcaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@GetMapping("/marcas/nueva")
	public String mostrarFormCrearNuevaMarca(Model model) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		model.addAttribute("listaCategorias",listaCategorias);
		model.addAttribute("marca", new Marca());
		return "marca_formulario";
	}
	
	@PostMapping("/marcas/guardar")
	public String guardarMarcas(Marca marca) {
		marcaRepository.save(marca);
		return "redirect:/";
	}
	
	@GetMapping("/marcas")
	public String listarMarcas(Model model) {
		log.info("ejecutando la amrca otraaa");
		List<Marca> marcas= marcaRepository.findAll();
		model.addAttribute("listaMarcas", marcas);
		log.info("ejecutando la amrca" + marcas);
		return "marcas";
	}

	@GetMapping("/marcas/editar/{id}")
	public String mostrarFormularioEditarProducto(@PathVariable("id") Integer id, Model modelo) {
		List<Categoria> listaCategoria = categoriaRepository.findAll();
		Marca marca = marcaRepository.findById(id).get();
		log.info("se encontro la marca con el id: "+id + " la marca: "+marca);
		modelo.addAttribute("marca", marca);
		modelo.addAttribute("listaCategorias", listaCategoria);

		return "marca_formulario";
	}
	
//	@GetMapping("/marcas/eliminar/{id}")
//	public String eliminarProducto(@PathVariable("id") Integer id, Model modelo) {
//		productoRepository.deleteById(id);
//		return "redirect:/productos";
//	}
}
