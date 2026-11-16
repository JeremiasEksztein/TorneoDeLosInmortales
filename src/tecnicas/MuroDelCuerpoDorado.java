package tecnicas;

import combatientes.Combatiente;

public class MuroDelCuerpoDorado extends Tecnica {
	private static final int REDUCCION = 20;
	
	public MuroDelCuerpoDorado() {
		super("Muro del Cuerpo Dorado", 20, TipoTecnica.DEFENSIVA);
	}

	@Override
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.activarDefensa(REDUCCION);
	}

	public int getReduccion() {
		return REDUCCION;
	}
}
