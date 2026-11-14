package torneo;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import combate.Pareja;
import eventos.Interesado;

public class Ronda {
	private int numero;
	private List<Cruce> cruces= new ArrayList<>();
	
	public void jugar(List<Interesado> i) {
		for(Cruce c: cruces) {
			c.resolver(numero,i);
		}
	}
	public List<Pareja> getGanadoras() {
		
		List<Pareja> ganadoras= new LinkedList<>();
		for(Cruce c : cruces) {
			ganadoras.add(c.getGanador());
		}
		
		return ganadoras;
	}
	public int getNumero() {
		return this.numero;
	}
	public List<Cruce> getCruces() {
		return this.cruces;
	}
}
