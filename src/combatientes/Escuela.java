package combatientes;

import java.util.List;

import equipamiento.Equipamiento;
import tecnicas.Tecnica;

public class Escuela {
	private String nombre;
	private List<Tecnica> tecnicasIniciales;
	private List<Equipamiento> equipamientoInicial;
	


	public Escuela(String nombre,
			List<Tecnica> tecnicasIniciales,
			List<Equipamiento> equipamientoInicial) {
		this.nombre = nombre;
		this.tecnicasIniciales = tecnicasIniciales;
		this.equipamientoInicial = equipamientoInicial;
		}
	

	public void preparar(Combatiente c) {
		
		for(Tecnica tec: tecnicasIniciales) {
			c.aprenderTecnica(tec);;
		}
		
		for(Equipamiento eq: equipamientoInicial) {
			c.equipar(eq);
		}
	}
	public String getNombre() {
		return this.nombre;
	}
	
}
