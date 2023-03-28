package sistema.inventario.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sistema.inventario.categoria.Categoria;
import sistema.inventario.producto.Producto;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> listaUsuarios= repository.findAll();
		model.addAttribute("listaUsuarios",listaUsuarios);
		return "usuarios";
	}
	
	@GetMapping("/usuarios/nuevo")
	public String mostrarFormularioDeRegistrodeUsuario(Model modelo) {
		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles",listaRoles);
		modelo.addAttribute("usuario", new Usuario()); 
		return "usuario_formulario";
	}
	
	@PostMapping("/usuarios/guardar")
	public String guardarUsuario(Usuario usuario) {
		repository.save(usuario);
		return "redirect:/usuarios";
	}
	
	

	@GetMapping("/usuarios/editar/{id}")
	public String mostrarFormularioEditarUsuario(@PathVariable("id") Integer id, Model modelo) {
		Usuario usuario= repository.findById(id).get();
		modelo.addAttribute("usuario", usuario);

		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles", listaRoles);

		return "usuario_formulario";
	}
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id, Model modelo) {
		repository.deleteById(id);
		return "redirect:/usuarios";
	}

}
