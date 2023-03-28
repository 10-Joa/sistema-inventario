package sistema.inventario.usuario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementable
	private Integer id;

	@Column(length = 45, nullable = false, unique = true)
	private String email;

	@Column(length = 10, nullable = false, unique = true)
	private String password;

	// con este join table creo la tabla usuario_rol y dentro agtego las joinclum de
	// id de usuario y rol
	@ManyToMany(cascade = CascadeType.MERGE  , fetch = FetchType.EAGER) //para q persiste a sus hijos
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public void anadirRol(Rol rol) {
		this.roles.add(rol);
	}
	
	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
	}

	public Usuario(Integer id, String email, String password, Set<Rol> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario(String email, String password, Set<Rol> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario() {
		super();
	}

	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Usuario(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + "]";
	}

}
