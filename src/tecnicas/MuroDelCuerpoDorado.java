package tecnicas;
import combatientes.Combatiente;

public class MuroDelCuerpoDorado extends Tecnica{
	private static final int REDUCCION=10;
	
	public MuroDelCuerpoDorado() {
		super("Muro del Cuerpo Dorado", 20, TipoTecnica.DEFENSIVA);
	}
	
	
	public MuroDelCuerpoDorado(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.activarDefensa(REDUCCION);
	}
}
