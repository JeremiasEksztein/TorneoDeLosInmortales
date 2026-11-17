package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class MuroDelCuerpoDorado extends Tecnica {
	private static final int REDUCCION = 5;
	
	public MuroDelCuerpoDorado() {
		super("Muro del Cuerpo Dorado", 20, TipoTecnica.DEFENSIVA);
	}

	@Override
	protected AccionRealizada aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.activarDefensa(REDUCCION);
		return new AccionRealizada(ejecutor.getNombre(), getNombre(), ejecutor.getNombre(), REDUCCION);
	}

	public int getReduccion() {
		return REDUCCION;
	}
}
