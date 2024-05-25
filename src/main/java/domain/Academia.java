
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
//@Access(AcessType.PROPERTY)
public class Academia extends Actor {

	private String Nombre_Comercial;


	@NotBlank
	public String getNombreComercial() {
		return this.Nombre_Comercial;
	}

	@NotBlank
	public void setNombreComercial(final String Nombre_Comercial) {
		this.Nombre_Comercial = Nombre_Comercial;
	}

}
