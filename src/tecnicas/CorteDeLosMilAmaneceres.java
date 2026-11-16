package tecnicas;

import combatientes.Combatiente;

public class CorteDeLosMilAmaneceres extends Tecnica {
	private static final int DAÑO_BASE = 25;
	
	public CorteDeLosMilAmaneceres() {
		super("Corte de los Mil Amaneceres", 10, TipoTecnica.OFENSIVA);
	}

	@Override
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int danioTotal = ejecutor.calcularDanioProvocado(DAÑO_BASE);
		rival.recibirDanio(danioTotal);
	}

	@Override
	public int getDaño() {
		return DAÑO_BASE;
	}
}
