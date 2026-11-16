package combatientes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import equipamiento.Equipamiento;
import tecnicas.Tecnica;

public class Escuela {

	private final String nombre;
	private final List<Tecnica> tecnicasIniciales;
	private final List<Equipamiento> equipamientoInicial;

	public Escuela(String nombre, List<Tecnica> tecnicasIniciales, List<Equipamiento> equipamientoInicial) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre de la escuela no puede ser nulo ni vacío.");
		}
		this.nombre = nombre;
		this.tecnicasIniciales = tecnicasIniciales != null ? new ArrayList<>(tecnicasIniciales) : new ArrayList<>();
		this.equipamientoInicial = equipamientoInicial != null ? new ArrayList<>(equipamientoInicial) : new ArrayList<>();
	}

	public void preparar(Combatiente c) {
		if (c == null) {
			throw new IllegalArgumentException("El combatiente a preparar no puede ser nulo.");
		}
		for (Tecnica t : tecnicasIniciales) {
			c.aprenderTecnica(t);
		}
		for (Equipamiento e : equipamientoInicial) {
			c.equipar(e);
		}
		// no llamar a reiniciarParaCombate() aca: equipar sube el máximo pero no el valor actual.
		// al iniciar el primer combate, Combate.ejecutar() restaurará vida y energía al máximo.
	}

	public String getNombre() {
		return this.nombre;
	}

	public List<Tecnica> getTecnicasIniciales() {
		return Collections.unmodifiableList(tecnicasIniciales); // DESPUES SE CHARLA SI VA O NO
	}

	public List<Equipamiento> getEquipamientoInicial() {
		return Collections.unmodifiableList(equipamientoInicial);// DESPUES SE CHARLA SI VA O NO
	}
}
