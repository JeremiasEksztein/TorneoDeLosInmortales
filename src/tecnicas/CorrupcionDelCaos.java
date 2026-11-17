package tecnicas;

import combatientes.Combatiente;
import efectos.Debilitamiento;
import eventos.AccionRealizada;

public class CorrupcionDelCaos extends Tecnica {
	private static final int DANIO_INICIAL = 5;
	private static final double REDUCCION_ATAQUE = 0.30;
	private static final int DURACION_TURNOS = 2;

	public CorrupcionDelCaos() {
		super("Corrupción del Caos", 15, TipoTecnica.OFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		int danioTotal = ejecutor.calcularDanioProvocado(DANIO_INICIAL);
		rival.recibirDanio(danioTotal);
		rival.aplicarEfectoTemporal(new Debilitamiento(REDUCCION_ATAQUE, DURACION_TURNOS));
		int danioEfectivo = vidaAntes - rival.getVida();
		return new AccionRealizada(ejecutor.getNombre(), getNombre(), rival.getNombre(), danioEfectivo);
	}

	@Override
	public int getDaño() {
		return DANIO_INICIAL;
	}
}