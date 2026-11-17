package equipamiento;

public class AnilloEspiritual extends Equipamiento{
	private static final double AUMENTO_ENERGIA_MAX=0.25;
	
	public AnilloEspiritual() {
		super("Anillo Espiritual", TipoEquipamiento.ANILLO);
    }
	
	@Override
	public int calcularEnergiaBonificada(int cantidad){
		return cantidad + (int) Math.round(cantidad * AUMENTO_ENERGIA_MAX);
	}
}
