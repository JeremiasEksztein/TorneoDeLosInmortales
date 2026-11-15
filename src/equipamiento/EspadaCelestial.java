package equipamiento;

public class EspadaCelestial extends Equipamiento{
	private static final int  BONO_DAÑO=10; 
	
	public EspadaCelestial() {
        super("Espada Celestial", TipoEquipamiento.ARMA, BONO_DAÑO, 0, 0, 0);
	}

	@Override
	public int getBonusVida() { return getVidaMax(); }

	@Override
	public int getBonusEnergia() { return getEnergiaMax(); }
}
