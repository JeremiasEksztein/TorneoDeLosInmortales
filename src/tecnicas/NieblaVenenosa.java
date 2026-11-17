package tecnicas;

import combatientes.Combatiente;
import efectos.Veneno;
import eventos.AccionRealizada;

public class NieblaVenenosa extends Tecnica {
	private static final int DANIO_INICIAL = 12;
	private static final int DANIO_VENENO = 8;
	private static final int DURACION_TURNOS = 3;

	public NieblaVenenosa() {
		super("Niebla Venenosa", 15, TipoTecnica.OFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		int danioTotal = ejecutor.calcularDanioProvocado(DANIO_INICIAL);
		rival.recibirDanio(danioTotal);
		rival.aplicarEfectoTemporal(new Veneno(DANIO_VENENO, DURACION_TURNOS));
		int danioEfectivo = vidaAntes - rival.getVida();
		return new AccionRealizada(ejecutor.getId(), getNombre(), rival.getId(), danioEfectivo);
	}

	@Override
	public int getDaño() {
		return DANIO_INICIAL;
	}
}