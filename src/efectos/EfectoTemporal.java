package efectos;

import combatientes.Combatiente;

public abstract class EfectoTemporal {
	private final String nombre;
	private int turnosRestantes;

	protected EfectoTemporal(String nombre, int duracionTurnos) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre del efecto no puede ser nulo ni vacío.");
		}
		if (duracionTurnos <= 0) {
			throw new IllegalArgumentException("La duración en turnos debe ser positiva.");
		}
		this.nombre = nombre;
		this.turnosRestantes = duracionTurnos;
	}

	public final void procesarTurno(Combatiente afectado) {
		if (estaActivo() && afectado != null && afectado.estaEnPie()) {
			aplicar(afectado);
			this.turnosRestantes--;
		}
	}

	protected abstract void aplicar(Combatiente afectado);

	public boolean estaActivo() {
		return this.turnosRestantes > 0;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getTurnosRestantes() {
		return this.turnosRestantes;
	}
}