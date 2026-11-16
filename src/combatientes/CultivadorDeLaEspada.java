package combatientes;

public class CultivadorDeLaEspada extends Combatiente {
	private static final double BONO_DANIO = 0.30; // Potencia 30% el daño que provoca
	private static final int VIDA_INICIAL = 150;
	private static final int ENERGIA_INICIAL = 300;

	public CultivadorDeLaEspada(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
	}

	@Override 
	public int calcularDanioProvocado(int cantidad) {
		int danioConArmas = super.calcularDanioProvocado(cantidad);
		return danioConArmas + (int) Math.round(danioConArmas * BONO_DANIO);
	}
}
