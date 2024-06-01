
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Solicitud extends DomainEntity {

	private Date			momento;
	private Estado_Curso	estado;
	private Alumno			alumno;
	private Curso			curso;


	/**
	 * @return the momento
	 */
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:MM")
	public Date getMomento() {
		return this.momento;
	}

	/**
	 * @param momento
	 *            the momento to set
	 */
	public void setMomento(final Date momento) {
		this.momento = momento;
	}

	/**
	 * @return the estado
	 */
	@NotNull
	public Estado_Curso getEstado() {
		return this.estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(final Estado_Curso estado) {
		this.estado = estado;
	}

	@Valid
	@ManyToOne(optional = true)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}

	@Valid
	@ManyToOne(optional = true)
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}

}
