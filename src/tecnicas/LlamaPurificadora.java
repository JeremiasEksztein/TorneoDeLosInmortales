package tecnicas;

import combatientes.Combatiente;
import efectos.Quemadura;
import eventos.AccionRealizada;

public class LlamaPurificadora extends Tecnica {
	private static final int DANIO_INICIAL = 18;
	private static final int DANIO_QUEMADURA = 12;
	private static final int DURACION_TURNOS = 2;

	public LlamaPurificadora() {
		super("Llama Purificadora", 20, TipoTecnica.OFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		int danioTotal = ejecutor.calcularDanioProvocado(DANIO_INICIAL);
		rival.recibirDanio(danioTotal);
		rival.aplicarEfectoTemporal(new Quemadura(DANIO_QUEMADURA, DURACION_TURNOS));
		int danioEfectivo = vidaAntes - rival.getVida();
		return new AccionRealizada(ejecutor.getNombre(), getNombre(), rival.getNombre(), danioEfectivo);
	}

	@Override
	public int getDaño() {
		return DANIO_INICIAL;
	}
}