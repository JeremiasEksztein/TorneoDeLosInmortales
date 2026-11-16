package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class CorteDeLosMilAmaneceres extends Tecnica {
	private static final int DAÑO_BASE = 25;
	
	public CorteDeLosMilAmaneceres() {
		super("Corte de los Mil Amaneceres", 10, TipoTecnica.OFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		int danioTotal = ejecutor.calcularDanioProvocado(DAÑO_BASE);
		rival.recibirDanio(danioTotal);
		int danioEfectivo = vidaAntes - rival.getVida();
		return new AccionRealizada(ejecutor.getId(), getNombre(), rival.getId(), danioEfectivo);
	}

	@Override
	public int getDaño() {
		return DAÑO_BASE;
	}
}
