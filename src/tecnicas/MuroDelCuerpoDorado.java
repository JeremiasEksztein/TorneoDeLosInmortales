package tecnicas;
import combatientes.Combatiente;

public class MuroDelCuerpoDorado extends Tecnica{
	private int REDUCCION;
	
	public MuroDelCuerpoDorado(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		
	}
}
