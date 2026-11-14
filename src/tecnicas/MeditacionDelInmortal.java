package tecnicas;
import combatientes.Combatiente;

public class MeditacionDelInmortal extends Tecnica{
	private int CURACION;
	
	public MeditacionDelInmortal(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		
	}
}
