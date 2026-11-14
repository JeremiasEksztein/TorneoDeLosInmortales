package tecnicas;
import combatientes.Combatiente;

public class CorteDeLosMilAmaneceres extends Tecnica {
	
	private static final int DAÑO_BASE=15;
	
	public CorteDeLosMilAmaneceres() {
		super("Corte de los Mil Amaneceres", 10, TipoTecnica.OFENSIVA);
	}
	
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		rival.recibirDaño(DAÑO_BASE);
	}
}
