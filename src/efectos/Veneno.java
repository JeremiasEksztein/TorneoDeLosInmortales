package efectos;

import combatientes.Combatiente;

public class Veneno extends EfectoTemporal {
	private final int danioPorTurno;

	public Veneno(int danioPorTurno, int duracionTurnos) {
		super("Veneno", duracionTurnos);
		if (danioPorTurno <= 0) {
			throw new IllegalArgumentException("El daño por turno del veneno debe ser positivo.");
		}
		this.danioPorTurno = danioPorTurno;
	}

	@Override
	protected String aplicarYDescribir(Combatiente afectado) {
		int vidaAntes = afectado.getVida();
		afectado.recibirDanioDirecto(this.danioPorTurno);
		int danio = vidaAntes - afectado.getVida();
		return danio > 0 ? "El combatiente " + afectado.getId() + " sufre daño por Veneno (" + danio + " puntos)" : null;
	}
}