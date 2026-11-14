package tecnicas;
import combatientes.Combatiente;

public class DominioDeLaNada extends Tecnica{
	private int DAÑO_BASE;
	private int ENERGIA_QUITADA;
	
	public DominioDeLaNada(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		
	}
}
