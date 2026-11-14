package combate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import combatientes.Combatiente;
import tecnicas.Tecnica;

public class Pareja {
	private int id;
	private Combatiente combatienteA;
	private Combatiente combatienteB;
	
	
	public int vidaTotal() {
		return (combatienteA.getVida() + combatienteB.getVida());
		
	}
	public boolean estaEnPie() {
		
		if(!combatienteA.estaEnPie()) {
			if(!combatienteB.estaEnPie())
				return false;
		}
		return true;
		
	}
	
	// le cambie el tipo porque se usa en "Combate" pero antes devolvia un void
	public List<Combatiente> integrantesEnOrden() {
		//No se como implementarla xD
		
		return new ArrayList<>();
				
	}
	public boolean puedeUsar(Combatiente ejecutor, Tecnica tecnica) {

		if(ejecutor==null) {
			throw new IllegalArgumentException("El ejecutor no puede ser nulo.");
		}
		
		if(tecnica==null) {
			throw new IllegalArgumentException("La técnica no puede ser nula.");
		}
        Combatiente companiero = obtenerCompaniero(ejecutor);

        // se bloquea si tu compañero uso la misma tecnica
        boolean bloqueadaPorCompaniero = companiero.usoEnEsteCombate(tecnica)
                && companiero.estaEnPie()
                && companiero.usosRestantes(tecnica) > 0;

        return !bloqueadaPorCompaniero;
    }
	
	private Combatiente obtenerCompaniero(Combatiente ejecutor) {
        if (ejecutor.equals(combatienteA)) {
            return combatienteB;
        }
        if (ejecutor.equals(combatienteB)) {
            return combatienteA;
        }
        throw new IllegalArgumentException("El combatiente no pertenece a esta pareja.");
    }
	
	public void reiniciarParaCombate() {
		
	}
	public int getId() {
		return this.id;
	}
	public Combatiente getCombatienteA() {
		return this.combatienteA;
	}
	public Combatiente getCombatienteB() {
		return this.combatienteB;
	}
	//falta el equals y hashcode
	public void recuperarEnergiaAsalto(int eNERGIA_POR_ASALTO) {
		combatienteA.recuperarEnergia(eNERGIA_POR_ASALTO);
		combatienteB.recuperarEnergia(eNERGIA_POR_ASALTO);		
	}
	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(id));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pareja other = (Pareja) obj;
		return id == other.id;
	}
	
	
}
