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

	public final String procesarTurno(Combatiente afectado) {
		if (estaActivo() && afectado != null && afectado.estaEnPie()) {
			String detalle = aplicarYDescribir(afectado);
			this.turnosRestantes--;
			return detalle;
		}
		return null;
	}

	protected abstract String aplicarYDescribir(Combatiente afectado);

	public int modificarDanioProvocado(int danio) {
		return danio;
	}

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