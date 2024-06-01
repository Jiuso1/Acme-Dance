
package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
//@Access(AcessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	private String	nombre;
	private String	apellidos;
	private String	Email;
	private String	telefono;
	private String	direccion;
	private int		rol; //1: admin, 2: academia, 3: alumno
	private String	username;
	private String	password;


	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	@Email
	public String getEmail() {
		return this.Email;
	}

	public void setEmail(final String email) {
		this.Email = email;
	}

	@Pattern(regexp = "^(\\(?(\\+34|0034|34)\\)?)?(6|7|8|9)(\\d{8})$")
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	@Range(min = 1, max = 3)
	public int getRol() {
		return this.rol;
	}

	public void setRol(final int rol) {
		this.rol = rol;
	}

	@Size(min = 5, max = 32)
	@Column(unique = true)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
