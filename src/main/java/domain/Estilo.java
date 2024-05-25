package domain;

import javax.persistence.Access;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
//@Access(AccessType.PROPERTY)
public class Estilo extends DomainEntity {
	
	private String nombre;
	private String descripcion;
	
	@NotBlank
	public String getNombre() {
		return nombre;
	}
	@NotBlank
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
