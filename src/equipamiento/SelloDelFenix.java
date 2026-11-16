package equipamiento;

public class SelloDelFenix extends Equipamiento{
	private static final double  AUMENTO_VIDA_MAX=0.4;	
	
	public SelloDelFenix() {
        super("Sello Del Fenix", TipoEquipamiento.SELLO);
	}

	@Override
	public int calcularVidaMaximaBonificada(int cantidad){
		return cantidad + (int) Math.round(cantidad * AUMENTO_VIDA_MAX);
	}
}
