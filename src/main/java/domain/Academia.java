
package domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
//@Access(AcessType.PROPERTY)
public class Academia extends Actor {

	private String			nombreComercial;
	private Set<Tutorial>	tutoriales;


	@NotBlank
	public String getNombreComercial() {
		return this.nombreComercial;
	}

	public void setNombreComercial(final String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	@OneToMany(mappedBy = "academia", cascade = CascadeType.ALL)
	public Set<Tutorial> getTutoriales() {
		return this.tutoriales;
	}

	public void setTutoriales(final Set<Tutorial> tutoriales) {
		this.tutoriales = tutoriales;
	}

}
