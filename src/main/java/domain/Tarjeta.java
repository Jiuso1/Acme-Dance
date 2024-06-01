
package domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
public class Tarjeta extends DomainEntity {

	private String	nombre;
	private String	marca;
	private long	numero;
	private int		mescad;
	private int		anocad;
	private int		cvv;
	private Alumno	alumno;


	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(final String marca) {
		this.marca = marca;
	}

	public long getNumero() {
		return this.numero;
	}

	public void setNumero(final long numero) {
		this.numero = numero;
	}

	@Range(min = 1, max = 12)
	public int getMescad() {
		return this.mescad;
	}

	public void setMescad(final int mescad) {
		this.mescad = mescad;
	}

	@Min(2024)
	public int getAnocad() {
		return this.anocad;
	}

	public void setAnocad(final int anocad) {
		this.anocad = anocad;
	}

	@Range(min = 100, max = 999)
	public int getCvv() {
		return this.cvv;
	}

	public void setCvv(final int cvv) {
		this.cvv = cvv;
	}

	@Valid
	@OneToOne(optional = true)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}
}
