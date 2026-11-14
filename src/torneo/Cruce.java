package torneo;

import java.util.List;

import combate.Pareja;
import eventos.Interesado;
import eventos.PublicadorAcontecimiento;
import eventos.ResultadoCombate;

public class Cruce {
	private Pareja parejaA;
	private Pareja parejaB;
	private PublicadorAcontecimiento publicador;
	private ResultadoCombate resultado;
	
	public void resolver(int numeroRonda, List<Interesado> i) {
		
	}
	public Pareja getGanador() {
		return (parejaA.getId()==resultado.getIdGanadora()?parejaA:parejaB);
	}
}
