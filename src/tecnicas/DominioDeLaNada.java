package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class DominioDeLaNada extends Tecnica {
	private static final int DAÑO_BASE = 15;
	private static final int ENERGIA_QUITADA = 15;
	
	public DominioDeLaNada() {
		super("Dominio de la Nada", 15, TipoTecnica.OFENSIVA);
	}
	
	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		rival.quitarEnergia(ENERGIA_QUITADA);
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
