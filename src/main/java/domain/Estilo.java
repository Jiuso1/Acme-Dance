
package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
//@Access(AccessType.PROPERTY)
public class Estilo extends DomainEntity {

	private String		nombre;
	private String		descripcion;
	private List<Curso>	cursos;


	@NotBlank
	@Column(unique = true)
	public String getNombre() {
		return this.nombre;
	}
	@NotBlank
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
	@OneToMany(mappedBy = "estilo", cascade = CascadeType.ALL)
	public List<Curso> getCursos() {
		return this.cursos;
	}
	public void setCursos(final List<Curso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
