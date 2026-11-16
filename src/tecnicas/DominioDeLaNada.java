package tecnicas;

import combatientes.Combatiente;

public class DominioDeLaNada extends Tecnica {
	private static final int DAÑO_BASE = 15;
	private static final int ENERGIA_QUITADA = 15;
	
	public DominioDeLaNada() {
		super("Dominio de la Nada", 15, TipoTecnica.OFENSIVA);
	}
	
	@Override
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		rival.quitarEnergia(ENERGIA_QUITADA);
		int danioTotal = ejecutor.calcularDanioProvocado(DAÑO_BASE);
		rival.recibirDanio(danioTotal);
	}

	@Override
	public int getDaño() {
		return DAÑO_BASE;
	}
}
