package sistema.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import sistema.inventario.usuario.Rol;
import sistema.inventario.usuario.Usuario;
import sistema.inventario.usuario.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TestEntityManager entityManager; 
	
	@Test 
	public void testCrearRoles() {
		 Rol rolAdmin = new Rol("Administrador");
		 Rol rolEditor = new Rol("Editor");
		 Rol rolVisitante = new Rol("Visitante");
		 entityManager.persist(rolAdmin);
		 entityManager.persist(rolEditor);
		 entityManager.persist(rolVisitante);



	}
	
	@Test 
	public void testCrearUsuarioconUnRol() {
		 Rol rolAdmin = entityManager.find(Rol.class, 1);
		 Usuario usuario=new Usuario("joaquin.10@gmail.com", "12345");

		 usuario.anadirRol(rolAdmin);
		 repository.save(usuario);
	}
	
	@Test 
	public void testCrearUsuarioconDosRoles() {
		 Rol rolEditor = entityManager.find(Rol.class, 2);
		 Rol rolVisitante = entityManager.find(Rol.class, 3);

		 Usuario usuario=new Usuario("joa.15@gmail.com", "123457");

		 usuario.anadirRol(rolEditor);
		 usuario.anadirRol(rolVisitante);
		 repository.save(usuario);
	}
	
	@Test 
	public void testAsignarRolaUsuarioExistente() {
		Usuario usuario = repository.findById(1).get();
		 Rol rolEditor = entityManager.find(Rol.class, 2);

		 usuario.anadirRol(rolEditor);
		 repository.save(usuario);
	}
	
	
	@Test 
	public void testEliminarRolDeUnUsuarioExistente() {
		Usuario usuario = repository.findById(1).get();
		 Rol rol = new Rol(2); //le pasamos el id del rol que vamos a elminare
		 usuario.eliminarRol(rol); //para que pueda eliminar tse tuvo q agregar el hashcod tostrng en el Rol

	}
	
	@Test 
	public void testCrearNuevoUsuarioConNuevoRol() {
		 Rol rolVenderdor = new Rol("Vendedor"); 
		 Usuario usuario= new Usuario("gabriela@gmail.com","111111");
		 usuario.anadirRol(rolVenderdor);
		 repository.save(usuario);

	}
	
	@Test 
	public void testObtenerUsuario() {
		Usuario usuario= repository.findById(1).get();
		entityManager.detach(usuario);
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getRoles());
	}
}
