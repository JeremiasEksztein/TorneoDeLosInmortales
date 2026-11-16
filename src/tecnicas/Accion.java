package tecnicas;

import combatientes.Combatiente;
import eventos.AccionRealizada;

public interface Accion {
	AccionRealizada ejecutar(Combatiente ejecutor, Combatiente rival);
	String getNombre();
}
