package equipamiento;

public class SelloDelFenix extends Equipamiento{
	private static final double  AUMENTO_VIDA_MAX=0.4;	
	
	public SelloDelFenix() {
        super("Sello Del Fenix", TipoEquipamiento.SELLO);
	}

	
	public int calcularDanioRecibidoBonificado(int cantidad){
		return  ((int)(cantidad * AUMENTO_VIDA_MAX)+ cantidad);
	}
}
