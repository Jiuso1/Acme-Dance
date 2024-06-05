
package ayuda;

import domain.Estado_Curso;

public class Ayuda {

	private int				a;
	private Estado_Curso	estado;
	private String			estilo;


	public Ayuda() {

	}

	public int getA() {
		return this.a;
	}

	public void setA(final int a) {
		this.a = a;
	}

	public Estado_Curso getEstado() {
		return this.estado;
	}

	public void setEstado(final Estado_Curso estado) {
		this.estado = estado;
	}

	public String getEstilo() {
		return this.estilo;
	}

	public void setEstilo(final String estilo) {
		this.estilo = estilo;
	}
}
