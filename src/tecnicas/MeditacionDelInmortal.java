package tecnicas;
import combatientes.Combatiente;

public class MeditacionDelInmortal extends Tecnica{
	private static final int CURACION;//no deberia de ir esto, recuperar vida, te da la vida inicial
	
	public MeditacionDelInmortal() {
		super("Meditacion del Inmortal", 2, TipoTecnica.RECUPERACION);
	}
	
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		ejecutor.recuperarEnergia(CURACION);
	}
}
