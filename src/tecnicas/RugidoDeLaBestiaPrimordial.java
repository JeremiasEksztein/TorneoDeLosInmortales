package tecnicas;

import combatientes.Combatiente;

public class RugidoDeLaBestiaPrimordial extends Tecnica {
	private static final int DAÑO_BASE = 40;
	
	public RugidoDeLaBestiaPrimordial() {
		super("Rugido de la Bestia Primordial", 20, TipoTecnica.OFENSIVA);
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
