
package domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Inheritance
@Entity
//@Access(AcessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	private String	nombre;
	private String	apellidos;
	private String	Email; //tipo email
	private int		telefono; //falta poner algo
	private String	direccion;


	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	@NotBlank
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getApellidos() {
		return this.apellidos;
	}

	@NotBlank
	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.Email;
	}

	public void setEmail(final String email) {
		this.Email = email;
	}

	@Pattern(regexp = "(+34|0034|34)?[ -](6|7)[ -]([0-9][ -]*){8}")
	public int getTelefono() {
		return this.telefono;
	}

	@Pattern(regexp = "(+34|0034|34)?[ -](6|7)[ -]([0-9][ -]*){8}")
	public void setTelefono(final int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}
}
