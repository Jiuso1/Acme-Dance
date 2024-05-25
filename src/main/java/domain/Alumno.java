
package domain;

import javax.persistence.Entity;

@Entity
//@Access(AcessType.PROPERTY)
public class Alumno extends Actor {

	private String tarjeta; //tiene que ser tipo tarjeta


	public String getTarjeta() {
		return this.tarjeta;
	}

	public void setTarjeta(final String tarjeta) {
		this.tarjeta = tarjeta;
	}
}
