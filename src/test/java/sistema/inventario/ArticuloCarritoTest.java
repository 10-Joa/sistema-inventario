package sistema.inventario;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import sistema.inventario.carrito.ArticuloCarrito;
import sistema.inventario.carrito.ArticuloCarritoRepository;
import sistema.inventario.producto.Producto;
import sistema.inventario.usuario.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTest {

	@Autowired
	private ArticuloCarritoRepository articuloCarritoRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void anadirCarrito() {
		Producto producto = entityManager.find(Producto.class, 2);
		Usuario usuario= entityManager.find(Usuario.class, 1);
		
		ArticuloCarrito articuloCarrito= new ArticuloCarrito(1,producto,usuario);
		articuloCarritoRepository.save(articuloCarrito);
	}
	
	@Test
	public void anadirMultiplesArticulos() {
		Usuario usuario= new Usuario(1);
		Producto producto1 = new Producto(2);
		Producto producto2 = new Producto(3);

		ArticuloCarrito articuloCarrito1= new ArticuloCarrito(3,producto1,usuario);
		ArticuloCarrito articuloCarrito2= new ArticuloCarrito(5,producto2,usuario);
		List<ArticuloCarrito> listaArticulosCarrito = new ArrayList<>();
		listaArticulosCarrito.add(articuloCarrito1);
		listaArticulosCarrito.add(articuloCarrito2);
		articuloCarritoRepository.saveAll(listaArticulosCarrito);
	}
	
	
	
	@Test
	public void listarArticulos() {
		List<ArticuloCarrito> articulos = articuloCarritoRepository.findAll();
		//para impimir esto debo tener un to string en aritucloCarrito
		articulos.forEach(System.out::println);
	}
	
	@Test
	public void testActualizararticuloCarrito() {
		List<ArticuloCarrito> articulos = articuloCarritoRepository.findAll();
		//para impimir esto debo tener un to string en aritucloCarrito
		articulos.forEach(System.out::println);
	}
}
