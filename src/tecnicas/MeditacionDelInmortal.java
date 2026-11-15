package tecnicas;
import combatientes.Combatiente;

public class MeditacionDelInmortal extends Tecnica{
	private static final int CURACION=1;//no deberia de ir esto, recuperar vida, te da la vida inicial
	
	public MeditacionDelInmortal() {
		super("Meditacion del Inmortal", 2, TipoTecnica.RECUPERACION);
	}
	
	public MeditacionDelInmortal(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.recuperarEnergia(CURACION);
	}
}
