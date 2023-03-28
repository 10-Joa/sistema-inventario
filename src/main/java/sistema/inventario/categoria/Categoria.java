package sistema.inventario.categoria;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import sistema.inventario.marca.Marca;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//autoincrementable
	private Integer id;
	
	@Column(length = 45, nullable = false , unique = true)
	private String nombre;
	 
	//quiere decir q muchas categorias pertenecen a una marca, cuando coloco el joincolum quiere decir q en esta clase va a estar esa marca_id agregada en la tabla
	@ManyToOne()
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	public Marca getMarca() {
		return marca;
	}

	@Override
	public String toString() {
		return  nombre;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Categoria(Integer id) {
		super();
		this.id = id;
	}

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	public Categoria() {
		super();
	}

	public Categoria(Integer id, String nombre, Marca marca) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
	}

	public Categoria(String nombre, Marca marca) {
		super();
		this.nombre = nombre;
		this.marca = marca;
	}
	
	
	
}
