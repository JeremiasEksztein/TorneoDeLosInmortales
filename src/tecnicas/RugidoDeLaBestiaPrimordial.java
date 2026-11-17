package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class RugidoDeLaBestiaPrimordial extends Tecnica {
	private static final int DAÑO_BASE = 40;
	
	public RugidoDeLaBestiaPrimordial() {
		super("Rugido de la Bestia Primordial", 20, TipoTecnica.OFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		int danioTotal = ejecutor.calcularDanioProvocado(DAÑO_BASE);
		rival.recibirDanio(danioTotal);
		int danioEfectivo = vidaAntes - rival.getVida();
		return new AccionRealizada(ejecutor.getNombre(), getNombre(), rival.getNombre(), danioEfectivo);
	}

	@Override
	public int getDaño() {
		return DAÑO_BASE;
	}
}
