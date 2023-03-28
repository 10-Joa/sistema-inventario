package sistema.inventario.producto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import sistema.inventario.categoria.Categoria;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String nombre;
	
	private float precio;
	
	//en resumen quiere decir que muchos productos estaran relacionados a una categoria -> con la llave foranero categoria_id
	@ManyToOne()
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	//con el fethtype y el @fetch ouedo ver tabals relacionadas
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
	private List<ProductoDetalles> detalles=new ArrayList<>();

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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void anadirDetalles(String nombre,String valor) {
		//el (nombre,valor, this) => this hace referencia al mappeBy = "producto"
		this.detalles.add(new ProductoDetalles(nombre,valor,this));
	}
	
	public void setDetalle(Integer id , String nombre, String valor) {
		this.detalles.add(new ProductoDetalles(id,nombre,valor,this));
	}

	public List<ProductoDetalles> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<ProductoDetalles> detalles) {
		this.detalles = detalles;
	}
	
	public Producto(Integer id, String nombre, float precio, Categoria categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Producto() {
		super();
	}

	public Producto(String nombre, float precio, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Producto(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Producto(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + "]";
	}
		
}
