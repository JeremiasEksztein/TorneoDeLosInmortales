package combatientes;

public class CultivadorDeLaEspada extends Combatiente{
	private int BONO_DAÑO;
	private static final int VIDA_INICIAL = 150; //dsp los cambiamos
	private static final int ENERGIA_INICIAL = 300;
	public CultivadorDeLaEspada(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
		
	}
	
	public int calcularDañoProvocado(int base) {
		return base;
	}
}
