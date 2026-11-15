package equipamiento;

public class AnilloEspiritual extends Equipamiento{
	
	private static final int AUMENTO_ENERGIA_MAX=40;
	
	public AnilloEspiritual() {
        super("Anillo Espiritual", TipoEquipamiento.ANILLO, 0, 0, 0, AUMENTO_ENERGIA_MAX);
    }

	@Override
	public int getBonusVida() { return getVidaMax(); }

	@Override
	public int getBonusEnergia() { return getEnergiaMax(); }
}
