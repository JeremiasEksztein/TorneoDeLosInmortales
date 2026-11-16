package equipamiento;

public class EspadaCelestial extends Equipamiento{
	private static final double BONO_DANIO = 0.3;

	public EspadaCelestial() {
        super("Espada Celestial", TipoEquipamiento.ARMA);
	}

	@Override
	public int calcularDanioBonificado(int cantidad){
		return cantidad + (int) Math.round(cantidad * BONO_DANIO);
	}
}
