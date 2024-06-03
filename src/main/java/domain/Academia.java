
package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
//@Access(AcessType.PROPERTY)
public class Academia extends Actor {

	private String			nombreComercial;
	private List<Tutorial>	tutoriales;
	private List<Curso>		cursos;


	@NotBlank
	public String getNombreComercial() {
		return this.nombreComercial;
	}

	public void setNombreComercial(final String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	@OneToMany(mappedBy = "academia", cascade = CascadeType.ALL)
	public List<Tutorial> getTutoriales() {
		return this.tutoriales;
	}

	public void setTutoriales(final List<Tutorial> tutoriales) {
		this.tutoriales = tutoriales;
	}
	@OneToMany(mappedBy = "academia", cascade = CascadeType.ALL)
	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(final List<Curso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		return this.nombreComercial;
	}

}
