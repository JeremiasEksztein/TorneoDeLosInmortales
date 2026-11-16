package equipamiento;

public class AnilloEspiritual extends Equipamiento{
	
	private static final double AUMENTO_ENERGIA_MAX=0.25;
	
	public AnilloEspiritual() {
        super("Anillo Espiritual", TipoEquipamiento.ANILLO);
    }

	
	 public int calcularEnergiaBonificada(int cantidad){
		return  ((int)(cantidad * AUMENTO_ENERGIA_MAX)+ cantidad);
	}
}
