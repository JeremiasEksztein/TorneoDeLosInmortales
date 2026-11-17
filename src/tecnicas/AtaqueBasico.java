package tecnicas;
import combatientes.Combatiente;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public class AtaqueBasico implements Accion {
	private static final int DANIO_BASE = 12;

	@Override
	public AccionRealizada ejecutar(Combatiente ejecutor, Combatiente rival) {
		int vidaAntes = rival.getVida();
		rival.recibirDanio(ejecutor.calcularDanioProvocado(DANIO_BASE));
		int danioEfectivo = vidaAntes - rival.getVida();
		return new AccionRealizada(ejecutor.getNombre(), getNombre(), rival.getNombre(), danioEfectivo);
	}

	@Override
	public String getNombre() {
		return "Ataque básico";
	}
}
