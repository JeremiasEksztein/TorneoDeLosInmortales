package equipamiento;

public class ArmaduraDorada extends Equipamiento{
	private static final double REDUCCION_DANIO_RECIBIDO = 0.5;
	
	public ArmaduraDorada() {
        super("Armadura Dorada", TipoEquipamiento.ARMADURA);
	}
	
	@Override
	public int calcularDanioRecibidoBonificado(int cantidad) {
		return cantidad - (int) Math.round(cantidad * REDUCCION_DANIO_RECIBIDO);
	}
}
