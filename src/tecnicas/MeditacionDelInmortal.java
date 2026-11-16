package tecnicas;

import combatientes.Combatiente;

public class MeditacionDelInmortal extends Tecnica {
	private static final int CURACION = 30;
	
	public MeditacionDelInmortal() {
		super("Meditacion del Inmortal", 2, TipoTecnica.RECUPERACION);
	}
	
	public MeditacionDelInmortal(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}

	@Override
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.recuperarVida(CURACION);
	}

	public int getCuracion() {
		return CURACION;
	}
}
