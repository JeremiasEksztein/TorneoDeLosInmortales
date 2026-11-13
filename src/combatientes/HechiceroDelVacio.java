package combatientes;

public class HechiceroDelVacio extends Combatiente {
	private int REDUCCION_COSTO;
	private static final int VIDA_INICIAL = 150; //dsp los cambiamos
	private static final int ENERGIA_INICIAL = 300;
	public HechiceroDelVacio(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
	}
	
	public int calcularCosto() {
		return REDUCCION_COSTO;
	}
}
