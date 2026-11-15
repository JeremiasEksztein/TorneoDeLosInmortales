package tecnicas;
import combatientes.Combatiente;

public class DominioDeLaNada extends Tecnica{
	private static final int DAÑO_BASE=10;
	private static final int ENERGIA_QUITADA=10;
	
	public DominioDeLaNada() {
		super("Dominio de la Nada", 15, TipoTecnica.OFENSIVA);
	}
	
	public DominioDeLaNada(String nombre, int costo, TipoTecnica tipo) {
		super(nombre, costo, tipo);
	}
	
	public void aplicarEfecto(Combatiente ejecutor, Combatiente rival) {
		rival.quitarEnergia(ENERGIA_QUITADA);
		rival.recibirDanio(DAÑO_BASE);
	}
}
