package combatientes;


import java.util.List;

import combate.CriterioAccion;
import combate.Pareja;
import equipamiento.Equipamiento;
import equipamiento.TipoEquipamiento;
import tecnicas.Accion;
import tecnicas.Tecnica;

public class CultivadorDeLaEspada extends Combatiente{
	private static final int BONO_DAÑO=10; // numero random, despues lo cambiamos
	private static final int VIDA_INICIAL = 150; //dsp los cambiamos
	private static final int ENERGIA_INICIAL = 300;

	public CultivadorDeLaEspada(int id, String nombre) {
		super(id, nombre, VIDA_INICIAL, ENERGIA_INICIAL);
	}

	public int calcularDanioProvocado(int cantidad){
		int danioTotal=	cantidad + BONO_DAÑO;
		for(Equipamiento equipo : getEquipamiento()) {
			if(equipo.getTipo() == TipoEquipamiento.ARMA) {
			 danioTotal+=equipo.getBonoDaño();
			}
		}
		return danioTotal;
	}

	public int calcularDanioRecibido(int cantidad) {
    for (Equipamiento equipo : getEquipamiento()) {
        if (equipo.getTipo() == TipoEquipamiento.ARMADURA) {
            return Math.max(0, cantidad - equipo.getDañoRecibido());
        }
    }
    return cantidad;
	}


	public int calcularCostoEnergia(int cantidad) {
    return cantidad;
	}
	

}
