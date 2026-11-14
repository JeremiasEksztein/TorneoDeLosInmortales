package tecnicas;
import combatientes.Combatiente;

public class RugidoDeLaBestiaPrimordial extends Tecnica{

	private static final int DAÑO_BASE=40;
	
	public RugidoDeLaBestiaPrimordial() {
		super("Rugido de la Bestia Primordial", 20, TipoTecnica.OFENSIVA);
	}

	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		rival.recibirDaño(DAÑO_BASE);
	}
}
