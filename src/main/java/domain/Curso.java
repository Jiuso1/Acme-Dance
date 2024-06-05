
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity

public class Curso extends DomainEntity {

	private String			titulo;
	private Date			fechaini;
	private Date			fechafin;
	private String			diaSemana;
	private String			hora;
	private Nivel			nivel;
	private List<Solicitud>	solicitudes;
	private Estilo			estilo;
	private Academia		academia;


	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaini() {
		return this.fechaini;
	}

	public void setFechaini(final Date fechaini) {
		this.fechaini = fechaini;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(final Date fechafin) {
		this.fechafin = fechafin;
	}

	@NotBlank
	public String getDiaSemana() {
		return this.diaSemana;
	}
	public void setDiaSemana(final String diaSemana) {
		this.diaSemana = diaSemana;
	}

	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
	public String getHora() {
		return this.hora;
	}
	public void setHora(final String hora) {
		this.hora = hora;
	}

	public Nivel getNivel() {
		return this.nivel;
	}
	public void setNivel(final Nivel nivel) {
		this.nivel = nivel;
	}

	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	public List<Solicitud> getSolicitudes() {
		return this.solicitudes;
	}

	public void setSolicitudes(final List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	@Valid
	@ManyToOne(optional = true)
	public Estilo getEstilo() {
		return this.estilo;
	}
	public void setEstilo(final Estilo estilo) {
		this.estilo = estilo;
	}
	@Valid
	@ManyToOne(optional = false)
	public Academia getAcademia() {
		return this.academia;
	}
	public void setAcademia(final Academia academia) {
		this.academia = academia;
	}

	@Override
	public String toString() {
		return this.titulo;
	}

}
