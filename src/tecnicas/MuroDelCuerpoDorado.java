package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class MuroDelCuerpoDorado extends Tecnica {
	private static final int REDUCCION = 20;
	
	public MuroDelCuerpoDorado() {
		super("Muro del Cuerpo Dorado", 20, TipoTecnica.DEFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.activarDefensa(REDUCCION);
		return new AccionRealizada(ejecutor.getId(), getNombre(), ejecutor.getId(), REDUCCION);
	}

	public int getReduccion() {
		return REDUCCION;
	}
}
