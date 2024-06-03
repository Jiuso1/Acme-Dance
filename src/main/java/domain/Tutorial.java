
package domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class Tutorial extends DomainEntity {

	private String		titulo;
	private String		descripcion;
	private String		video;
	private Academia	academia;


	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@URL
	public String getVideo() {
		return this.video;
	}

	public void setVideo(final String video) {
		this.video = video;
	}

	@Valid
	@ManyToOne(optional = false)
	public Academia getAcademia() {
		return this.academia;
	}

	public void setAcademia(final Academia academia) {
		this.academia = academia;
	}

}
