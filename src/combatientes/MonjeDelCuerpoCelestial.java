package combatientes;

public class MonjeDelCuerpoCelestial extends Combatiente{
	private int REDUCCION_DAÑO;
	private static final int VIDA_INICIAL = 150; //dsp los cambiamos
	private static final int ENERGIA_INICIAL = 300;
	public MonjeDelCuerpoCelestial(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
	}
	
	public int calcularDañoRecibido(int daño) {
		return daño;
	}
}
