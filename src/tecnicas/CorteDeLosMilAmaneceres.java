package tecnicas;
import combatientes.Combatiente;

public class CorteDeLosMilAmaneceres extends Tecnica {
	
	private static final int DAÑO_BASE=15;
	
	public CorteDeLosMilAmaneceres() {
		super("Corte de los Mil Amaneceres", 10, TipoTecnica.OFENSIVA);
	}
	
	public CorteDeLosMilAmaneceres(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		rival.recibirDanio(DAÑO_BASE);
	}
}
