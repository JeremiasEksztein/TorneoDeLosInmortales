package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class MeditacionDelInmortal extends Tecnica {
	private static final int CURACION = 30;
	
	public MeditacionDelInmortal() {
		super("Meditacion del Inmortal", 2, TipoTecnica.RECUPERACION);
	}
	
	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = ejecutor.getVida();
		ejecutor.recuperarVida(CURACION);
		int puntosCurados = ejecutor.getVida() - vidaAntes;
		return new AccionRealizada(ejecutor.getNombre(), getNombre(), ejecutor.getNombre(), puntosCurados);
	}

	public int getCuracion() {
		return CURACION;
	}
}
