package equipamiento;

public class SelloDelFenix extends Equipamiento{
	private static final int  AUMENTO_VIDA_MAX=30;	
	
	public SelloDelFenix() {
        super("Sello Del Fenix", TipoEquipamiento.SELLO, 0, 0, AUMENTO_VIDA_MAX, 0);
	}

	@Override
	public int getBonusVida() { return getVidaMax(); }

	@Override
	public int getBonusEnergia() { return getEnergiaMax(); }
}
