package equipamiento;

public class EspadaCelestial extends Equipamiento{
	private static final double BONO_DAÑO=0.3; 
	
	public EspadaCelestial() {
        super("Espada Celestial", TipoEquipamiento.ARMA);
	}

	
	public int calcularDanioBonificado(int cantidad){
		return  ((int)(cantidad * BONO_DAÑO)+ cantidad);
	}
}
