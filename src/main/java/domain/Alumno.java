
package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
//@Access(AcessType.PROPERTY)
public class Alumno extends Actor {

	private Tarjeta			tarjeta;
	private List<Solicitud>	solicitudes;


	@OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL)
	public Tarjeta getTarjeta() {
		return this.tarjeta;
	}

	public void setTarjeta(final Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
	public List<Solicitud> getSolicitudes() {
		return this.solicitudes;
	}

	public void setSolicitudes(final List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

}
