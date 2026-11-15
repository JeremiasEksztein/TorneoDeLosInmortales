package equipamiento;

public class ArmaduraDorada extends Equipamiento{
	private static final int  REDUCCION_DAÑO_RECIBIDO=10;
	
	public ArmaduraDorada() {
        super("Armadura Dorada", TipoEquipamiento.ARMADURA, 0,REDUCCION_DAÑO_RECIBIDO, 0, 0);
	}

	@Override
	public int getBonusVida() { return getVidaMax(); }

	@Override
	public int getBonusEnergia() { return getEnergiaMax(); }
}
