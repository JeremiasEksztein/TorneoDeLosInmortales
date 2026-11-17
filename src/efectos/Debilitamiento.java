package efectos;

import combatientes.Combatiente;

public class Debilitamiento extends EfectoTemporal {
	private final double factorReduccion;

	public Debilitamiento(double factorReduccion, int duracionTurnos) {
		super("Debilitamiento", duracionTurnos);
		if (factorReduccion <= 0.0 || factorReduccion >= 1.0) {
			throw new IllegalArgumentException("El factor de reducción debe estar entre 0 y 1.");
		}
		this.factorReduccion = factorReduccion;
	}

	@Override
	public int modificarDanioProvocado(int danio) {
		if (!estaActivo()) {
			return danio;
		}
		return (int) Math.round(danio * (1.0 - this.factorReduccion));
	}
	
	@Override
	protected String aplicarYDescribir(Combatiente afectado) {
		int porcentaje = (int) Math.round(this.factorReduccion * 100);
		return "El combatiente " + afectado.getNombre() + " está bajo Debilitamiento (-" + porcentaje + "% de daño)";
	}
}