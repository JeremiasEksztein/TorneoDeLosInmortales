package combatientes;


import java.util.List;

import combate.CriterioAccion;
import combate.Pareja;
import equipamiento.Equipamiento;
import equipamiento.TipoEquipamiento;
import tecnicas.Accion;
import tecnicas.Tecnica;

public class CultivadorDeLaEspada extends Combatiente{
	private static final double BONO_DAÑO=0.5; // numero random, despues lo cambiamos
	private static final int VIDA_INICIAL = 150; //dsp los cambiamos
	private static final int ENERGIA_INICIAL = 300;

	public CultivadorDeLaEspada(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
	}
	@Override 
	public int calcularDanioProvocado(int cantidad){
		int danioTotal=	super.calcularDanioProvocado(cantidad);

		return  (danioTotal + (int)(danioTotal * BONO_DAÑO));
	}

	public int calcularDanioRecibido(int cantidad) {
    for (Equipamiento equipo : getEquipamiento()) {
        if (equipo.getTipo() == TipoEquipamiento.ARMADURA) {
            return Math.max(0, cantidad - equipo.getDañoRecibido());
        }
    }
    return cantidad;
	}

}
