package equipamiento;

public class ArmaduraDorada extends Equipamiento{
	private static final double  REDUCCION_DAÑO_RECIBIDO=0.5;
	
	public ArmaduraDorada() {
        super("Armadura Dorada", TipoEquipamiento.ARMADURA);
	}

	
	public int calcularReduccionDanioRecibidoEquipamiento(int cantidad){
		return  ((int)(cantidad * REDUCCION_DAÑO_RECIBIDO)- cantidad);
	}
}
