package efectos;

import combatientes.Combatiente;

public class Quemadura extends EfectoTemporal {
	private final int danioPorTurno;

	public Quemadura(int danioPorTurno, int duracionTurnos) {
		super("Quemadura", duracionTurnos);
		if (danioPorTurno <= 0) {
			throw new IllegalArgumentException("El daño por turno de la quemadura debe ser positivo.");
		}
		this.danioPorTurno = danioPorTurno;
	}

	@Override
	protected String aplicarYDescribir(Combatiente afectado) {
		int vidaAntes = afectado.getVida();
		afectado.recibirDanioDirecto(this.danioPorTurno);
		int danio = vidaAntes - afectado.getVida();
		return danio > 0 ? "El combatiente " + afectado.getNombre() + " sufre daño por Quemadura (" + danio + " puntos)" : null;
	}
}